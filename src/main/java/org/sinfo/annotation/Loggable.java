package org.sinfo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.sinfo.business.service.impl.TagBSImpl;
/**
 * 
 * @author yelouardi
 * Annotation for log info,error,debug,warn
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Loggable {
	
	enum level{
		  FATAL,
		  ERROR,
		  WARN,
		  INFO,
		  DEBUG
	}
	/**
	 * Message for log
	 */
	String message="";
	/**
	 * Type log
	 */
	level type=level.INFO;
	/**
	 * Qualified name class
	 */
	String className="";
	String message();
	Class<?> calss();
	level type();
}