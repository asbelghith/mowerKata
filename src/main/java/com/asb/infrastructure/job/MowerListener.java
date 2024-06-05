package com.asb.infrastructure.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class MowerListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("---------- START JOB EXECUTION ----------");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("---------- TERMINATE JOB EXECUTION ----------");
    }
}