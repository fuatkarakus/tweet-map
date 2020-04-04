package com.advancedatabase.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TweetCollector implements Runnable, InitializingBean {

    private Thread thread;

    public TweetCollector() {
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            log.info("Thread Started With Name - {}", Thread.currentThread().getName());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Thread afterPropertiesSet With Name - {}", Thread.currentThread().getName());
    }
}
