package com.example.integration.config;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.scheduling.config.FixedRateTask;

//@Configuration
//@EnableIntegration
public class IntegrationDSLConfig {
	
	@Autowired
	Transformer transformer;
	
	//provides a builder format defining the integration flow
	//which takes input from MessageSources, Strings, or MessageChannels using from(), 
	//optionally filter using .filter
	//perform transformations using transform(), and 
	//produce results using handle()
	@Bean
	public IntegrationFlow copyAndTransformIntegration() {
		return IntegrationFlows.from(sourceDirectory(), 
				c -> c.poller(Pollers.fixedDelay(10000)))
				.filter(onlyTxt()) //message filter
				.transform(transformer, "transform")
				.channel("alphabet")
				.channel(fromSource()) //flow to another message channel(s)
				//.handle(targetDirectory()) //single service activator
				.get();
		
				//other possible methods include 
				//split (break collections into individual items
				//aggregate (into collections)
				//resequence

	}
	
	//pub sub channel
	@Bean
	public MessageChannel pubSubFileChannel() {
	    return new PublishSubscribeChannel();
	}
	
	//Input Channel Adapter
	//defining the message source for from()
	//Message source is where messages come from external places
	@Bean
	public MessageSource<File> sourceDirectory() {
		//We will use file adapter for our use case
		FileReadingMessageSource messageSource = new FileReadingMessageSource();
		messageSource.setDirectory(new File("Source"));
		return messageSource;
	}
	
	//Service activator
	//this is the place where we perform the results
	//setExpectReply to false indicates that this is a unidirectional pipe
	@Bean
    public MessageHandler targetDirectory() {
        FileWritingMessageHandler writer = new FileWritingMessageHandler(new File("DSL_Destination"));
		writer.setAutoCreateDirectory(true); 
        writer.setExpectReply(false); // end of pipeline, reply not needed
		writer.setFileExistsMode(FileExistsMode.REPLACE_IF_MODIFIED);
        return writer;
    }
	
	@Bean
    public MessageHandler anotherDirectory() {
        FileWritingMessageHandler writer = new FileWritingMessageHandler(new File("DSL_Destination2"));
		writer.setAutoCreateDirectory(true); 
        writer.setExpectReply(false); // end of pipeline, reply not needed
		writer.setFileExistsMode(FileExistsMode.REPLACE_IF_MODIFIED);
        return writer;
    }

	//Message filter
	//Generic selector is provided by Spring Integration
	//to define simple filter
	@Bean
	public GenericSelector<File> onlyTxt() {
	    return new GenericSelector<File>() {
	        @Override
	        public boolean accept(File source) {
	          return source.getName().endsWith(".txt");
	        }
	    };
	}
	
	//If you would like point-to-point use
	//public MessageChannel fromSource(){
	//return MessageChannels.queue.get();}
	@Bean
	public MessageChannel fromSource() {
		return MessageChannels.publishSubscribe("pubsub").get();
	}
	
	//sending to our subscribers
	@Bean
	public IntegrationFlow fileWriter() {
	    return IntegrationFlows.from(fromSource())
	      .handle(targetDirectory())
	      .get();
	}
	
	//sending to our subscribers
	@Bean
	public IntegrationFlow fileWriter2() {
	    return IntegrationFlows.from(fromSource())
	      .handle(anotherDirectory())
	      .get();
	}
	
	//queue capacity of 1000
	//getPayload retrieve the actual message, discarding all headers
	//prioritize file based on name
	@Bean
	public PriorityChannel alphabeticalPriority() {
	    return new PriorityChannel(1000, (left, right) -> 
	      ((File)left.getPayload()).getName().compareTo(
	        ((File)right.getPayload()).getName()));
	}
	
}