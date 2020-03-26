package com.example.integration.config;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

//@Configuration
//@EnableIntegration
public class IntegrationSimpleConfig {
	
	//pub sub channel
	@Bean
	public MessageChannel pubSubFileChannel() {
	    return new PublishSubscribeChannel();
	}
	
	//a P2P channel
	@Bean
	public MessageChannel fileInputChannel() {
		return new DirectChannel(); //defining a Point-to-Point (P2P) channel
	}
	
	//When the value is specified as Direct Channel, then it's a P2P, meaning that 
	//the other side can only be one channel, thus if there are four files, 
	//we cannnot guarantee that all four files will go to both folders Destination1 and 2
	//However, if we want to distribute, we can change the value to PublishSubscribeChannel
	//Try replace all value of the methods below to fileInputChannel and see
	//the results in Destination and Destination2
	@Bean
	@InboundChannelAdapter(value = "pubSubFileChannel", poller = @Poller(fixedDelay = "1000"))
	public FileReadingMessageSource fileReadingMessageSource() {
		
		//specifying what kind of files it will read
		CompositeFileListFilter<File> filter = new CompositeFileListFilter<>();
		filter.addFilter(new SimplePatternFileListFilter("*.txt"));
		
		
		FileReadingMessageSource reader = new FileReadingMessageSource();
		reader.setDirectory(new File("Source"));
		reader.setFilter(filter);  //optional; demonstrate how we can filter
		return reader;
	}
	
	@Bean
	@ServiceActivator(inputChannel = "pubSubFileChannel")
	public FileWritingMessageHandler fileWritingMessageHandler() {
		FileWritingMessageHandler writer = 
				new FileWritingMessageHandler(new File("Destination"));
		writer.setAutoCreateDirectory(true); 
		writer.setExpectReply(false);
		writer.setFileExistsMode(FileExistsMode.REPLACE_IF_MODIFIED);
		return writer;
	}
	
	@Bean
	@ServiceActivator(inputChannel = "pubSubFileChannel")
	public FileWritingMessageHandler fileWritingMessageHandler2() {
		FileWritingMessageHandler writer = 
				new FileWritingMessageHandler(new File("Destination2"));
		writer.setAutoCreateDirectory(true); 
		writer.setExpectReply(false);
		writer.setFileExistsMode(FileExistsMode.REPLACE_IF_MODIFIED);
		return writer;
	}
}