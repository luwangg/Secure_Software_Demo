package edu.hm.bugcoin.task;
/*
 * Projekt: software
 * Autor: Maximilian Pachl
 * 2016-10-18 17:05
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import edu.hm.bugcoin.service.BankAccountService;
import edu.hm.bugcoin.service.CustomerService;
import edu.hm.bugcoin.service.TransactionRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskWorker extends Thread
{

    // ----------------------------------------------------------------------------------
    //  Konstanten
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Objektvariablen
    // ----------------------------------------------------------------------------------

    private final List<Task> tasks = new ArrayList<>();
    private boolean running = true;

    @Autowired CustomerService customerService;
    @Autowired BankAccountService bankAccountService;
    @Autowired TransactionRepository transactionRepository;
    @Autowired ApplicationContext ctx;


    // ----------------------------------------------------------------------------------
    //  Konstruktoren
    // ----------------------------------------------------------------------------------

    public TaskWorker()
    {
        setDaemon(true);
        start();
    }


    // ----------------------------------------------------------------------------------
    //  Getter
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Setter
    // ----------------------------------------------------------------------------------

    public void terminate()
    {
        running = false;
        tasks.notifyAll();
    }


    // ----------------------------------------------------------------------------------
    //  Auskunftsmethoden
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Aendernde Methoden
    // ----------------------------------------------------------------------------------

    @Override public void run()
    {
        System.out.println("TaskWorker up and running");
        while(running)
        {
            try
            {
                // wait for next command
                if (tasks.size() == 0)
                {
                    synchronized (this)
                    {
                        this.wait();
                    }
                }

                synchronized (this)
                {
                    tasks.get(0).execute();
                    tasks.remove(0);
                }
            } catch(final InterruptedException ignored) {}
        }

        System.out.println("Task worker failed!");
    }

    public synchronized void add(final Task task)
    {
        // satisfy @Autowire members in the task
        ctx.getAutowireCapableBeanFactory().autowireBeanProperties(
                task, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);

        tasks.add(task);
        this.notifyAll();
    }


    // ----------------------------------------------------------------------------------
    //  Standardmethoden
    // ----------------------------------------------------------------------------------

}
