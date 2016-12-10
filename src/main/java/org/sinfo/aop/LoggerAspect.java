package org.sinfo.aop;

import java.util.Arrays;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.sinfo.annotation.Loggable;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author yelouardi
 * Aspect for trace logger
 *
 */
@Aspect
@Component
public class LoggerAspect {
	private  StopWatch stopwatch;
	public StopWatch getStopWatch(){
		if(stopwatch==null){
			stopwatch = new StopWatch(LoggerAspect.class.getName());
		}
		return stopwatch;
	}


	
	
	/**Log Befor execution methode
	 * @param point
	 * @param loggable
	 */
	@Before(value = "@annotation(loggable)", argNames = "point,loggable")
	public void logBefore(JoinPoint point, Loggable loggable){
		if(!getStopWatch().isRunning()){
			getStopWatch().start();
		}
		String nameMethode=MethodSignature.class.cast(point.getSignature()).getMethod().getName();
	    String args=Arrays.toString(point.getArgs());
	    Logger.getLogger(loggable.calss()).log(Level.toLevel(loggable.type().name(),null), ("Start Methode : "+nameMethode+":"+ args));
	    Logger.getLogger(loggable.calss()).log(Level.toLevel(loggable.type().name(),null), (loggable.message()));
	    Logger.getLogger(loggable.calss()).log(Level.toLevel(loggable.type().name(),null), ("Execution for : "+nameMethode+":"+ args));
	}
	
	/**Log resultat after execution methode
	 * @param point
	 * @param loggable
	 * @param retVal
	 * @throws Throwable
	 */
	@AfterReturning(value = "@annotation(loggable)", argNames = "point,loggable,retVal", returning= "retVal")
	public void logAfter(JoinPoint  point, Loggable loggable,Object retVal) throws Throwable{
		String nameMethode=MethodSignature.class.cast(point.getSignature()).getMethod().getName();
	    String args=Arrays.toString(point.getArgs());
	    ObjectMapper mapper=new ObjectMapper();
	    if(getStopWatch().isRunning()){
			getStopWatch().stop();
		}
	    Logger.getLogger(loggable.calss()).log(Level.toLevel(loggable.type().name(),null), ("Finish Excecution :"+loggable.message()));
	    Logger.getLogger(loggable.calss()).log(Level.toLevel(loggable.type().name(),null), ("Finish Methode : in time : "+stopwatch.getTotalTimeSeconds()+"S For Methode "+nameMethode+":"+ args));
	    Logger.getLogger(loggable.calss()).log(Level.toLevel(loggable.type().name(),null), ("Finish Resultat : in time : "+stopwatch.getTotalTimeSeconds()+"S For Methode "+nameMethode+":"+ mapper.writeValueAsString(retVal)));

	}
	
	
	
	
}
