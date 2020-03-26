package com.example.integration.config;

import java.io.File;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

//@Configuration
//@EnableIntegration
//@IntegrationComponentScan
public class IntegrationRecipientRoutingConfig {
	
		@Autowired
		Transformer transformer;
	
		//define recipient list based on content
		@Bean
		public IntegrationFlow classify() {
			return IntegrationFlows.from(sourceDirectory(), 
					c -> c.poller(Pollers.fixedDelay(1000)))
					.routeToRecipients(route -> route
							.recipient(txtSource(), onlyTxt())
							.recipient(xmlSource(), onlyXml())
							.recipient(sqlSource(), onlySql())
							)
					.get();	
		}
							
							
		@Bean
		public QueueChannel txtSource() {
			return new QueueChannel();
		}
		
		@Bean
		public QueueChannel xmlSource() {
			return new QueueChannel();
		}
		
		@Bean
		public QueueChannel sqlSource() {
			return new QueueChannel();
		}
		
		@Bean
		public MessageSource<File> sourceDirectory() {
			//We will use file adapter for our use case
			FileReadingMessageSource messageSource = new FileReadingMessageSource();
			messageSource.setDirectory(new File("Source"));
			return messageSource;
		}
		
		@Bean
	    public MessageHandler txtDirectory() {
	        FileWritingMessageHandler writer = new FileWritingMessageHandler(new File("Text"));
			writer.setAutoCreateDirectory(true); 
	        writer.setExpectReply(false); // end of pipeline, reply not needed
			writer.setFileExistsMode(FileExistsMode.REPLACE_IF_MODIFIED);
	        return writer;
	    }
		
		@Bean
	    public MessageHandler xmlDirectory() {
	        FileWritingMessageHandler writer = new FileWritingMessageHandler(new File("Xml"));
			writer.setAutoCreateDirectory(true); 
	        writer.setExpectReply(false); // end of pipeline, reply not needed
			writer.setFileExistsMode(FileExistsMode.REPLACE_IF_MODIFIED);
	        return writer;
	    }
		
		@Bean
	    public MessageHandler sqlDirectory() {
	        FileWritingMessageHandler writer = new FileWritingMessageHandler(new File("Sql"));
			writer.setAutoCreateDirectory(true); 
	        writer.setExpectReply(false); // end of pipeline, reply not needed
			writer.setFileExistsMode(FileExistsMode.REPLACE_IF_MODIFIED);
	        return writer;
	    }
		
		@Bean
		public IntegrationFlow txtWriter() {
		    return IntegrationFlows.from(txtSource())
		    		.bridge(e -> e.poller(Pollers.fixedDelay(100)))

		    		.handle(txtDirectory())
		    		.get();
		}
		
		@Bean
		public IntegrationFlow xmlWriter() {
		    return IntegrationFlows.from(xmlSource())
		    		.bridge(e -> e.poller(Pollers.fixedDelay(100)))
		      .handle(xmlDirectory())
		      .get();
		}
		
		@Bean
		public IntegrationFlow sqlWriter() {
		    return IntegrationFlows.from(sqlSource())
		    		.bridge(e -> e.poller(Pollers.fixedDelay(100)))
		      .handle(sqlDirectory())
		      .get();
		}

		@Bean
		public GenericSelector<File> onlyTxt() {
		    return new GenericSelector<File>() {
		        @Override
		        public boolean accept(File source) {
		          return source.getName().endsWith(".txt");
		        }
		    };
		}
		
		@Bean
		public GenericSelector<File> onlyXml() {
		    return new GenericSelector<File>() {
		        @Override
		        public boolean accept(File source) {
		          return source.getName().endsWith(".xml");
		        }
		    };
		}
		
		@Bean
		public GenericSelector<File> onlySql() {
		    return new GenericSelector<File>() {
		        @Override
		        public boolean accept(File source) {
		          return source.getName().endsWith(".sql");
		        }
		    };
		}
		
}
