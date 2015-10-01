/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.app.scheduler;



import java.util.List;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 *
 * @author Mani
 * @
 */


public class QuartzScheduler {

    private List<String> jobs;
   


    @Autowired
    Scheduler schedulerbean;

       
    
     
    /**
     * Add Jobs to Quartz Scheduler
     *
     * @param jobId
     * @param groupname
     * @param triggername
     * @param crontab
     * @param jobclass
     * @return success if job added sucessfully
     * @throws SchedulerException
     */
    public boolean addJob(String jobId, String groupname, String triggername, String crontab, Class jobclass) throws SchedulerException {

        JobDetail job = JobBuilder.newJob(jobclass)
                .withIdentity(jobId, groupname).build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(triggername, groupname)
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(crontab))
                .build();

        return addJob(job, trigger);
    }

    /**
     *
     * @param jobdetail
     * @param trigger
     * @return success if job added sucessfully
     * @throws SchedulerException
     */
    public boolean addJob(JobDetail jobdetail, Trigger trigger) throws SchedulerException {
        JobDetail oldjob = schedulerbean.getJobDetail(jobdetail.getKey());

        if (oldjob != null) {
            schedulerbean.deleteJob(oldjob.getKey());
        }

        schedulerbean.scheduleJob(jobdetail, trigger);
        System.out.println ("Adding Job :" + jobdetail.toString() +" Trigger :" + trigger.toString());
        return true;
    }

    /**
     * Removes Jobs from Quartz Scheduler
     *
     * @param jobId
     * @param groupname
     * @return success if job deleted sucessfully
     * @throws SchedulerException
     */
    public boolean removeJob(String jobId, String groupname) throws SchedulerException {

        return schedulerbean.deleteJob(JobKey.jobKey(jobId, groupname));

    }
    @PostConstruct
    public void scheduleJobs() throws SchedulerException 
    {
    	
    	System.out.println("-----------Scheduling Jobs Start----------------------------");
    	System.out.println("-----------Scheduling Jobs End----------------------------");
    }
    
}
