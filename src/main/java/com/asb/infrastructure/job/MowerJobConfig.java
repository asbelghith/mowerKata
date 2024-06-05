package com.asb.infrastructure.job;

import com.asb.domain.entity.Lawn;
import com.asb.domain.entity.Mower;
import com.asb.domain.service.MowItNowService;
import com.asb.infrastructure.adapter.ReaderAdapter;
import com.asb.infrastructure.adapter.WriterAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MowerJobConfig {
    @Value("${management.static.constants.job_name}")
    private String jobName;
    @Value("${management.static.constants.step_name}")
    private String stepName;
    @Value("${management.static.constants.file.output}")
    private String outputPath;

    @Bean
    public Job mowItNowJob(JobRepository jobRepository, @Qualifier("mowerStep") Step mowItNowStep) {
        return new JobBuilder(jobName, jobRepository)
                .listener(new MowerListener())
                .start(mowItNowStep)
                .build();
    }

    @Bean(name = "mowerStep")
    public Step mowItNowStep(JobRepository jobRepository,
                             ItemProcessor<Lawn, List<Mower>> processor,
                             PlatformTransactionManager transactionManager) {
        return new StepBuilder(stepName, jobRepository)
                .<Lawn, List<Mower>>chunk(1, transactionManager)
                .reader(ReaderAdapter::readInputFile)
                .processor(processor)
                .writer(mowers -> WriterAdapter.writeOutputFile(outputPath, mowers.getItems().getFirst()))
                .build();
    }

    @Bean
    public ItemProcessor<Lawn, List<Mower>> processor() {
        return MowItNowService::processInstructions;
    }

    private JobExecutionListener jobListener() { return new MowerListener(); }
}
