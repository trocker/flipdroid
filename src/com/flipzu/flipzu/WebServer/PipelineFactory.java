/**
* Copyright 2011 Flipzu
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*  
*  Contributors: 
*  		Dario Rapisardi <dario@rapisardi.org>
*  		Nicolás Gschwing <nicolas@gschwind.com.ar>
*/
package com.flipzu.flipzu.WebServer;
import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;


public class PipelineFactory implements ChannelPipelineFactory {

	private FlipzuHandler handler;
	
	public PipelineFactory(FlipzuHandler handler) {
		this.handler = handler;
	}

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = pipeline();
		
		pipeline.addLast("http-decoder", new HttpRequestDecoder());
		pipeline.addLast("http-encoder", new HttpResponseEncoder());

		pipeline.addLast("client-handler", new HttpHandlerLocal());
		// buffer writer for new connections
		pipeline.addLast("buffer-writer", handler);

		return pipeline;	
	}

}
