package com.laowuandhisfriends.nioserver.http;

import com.laowuandhisfriends.nioserver.IMessageReader;
import com.laowuandhisfriends.nioserver.IMessageReaderFactory;

/**
 * Created by jjenkov on 18-10-2015.
 */
public class HttpMessageReaderFactory implements IMessageReaderFactory {

    public HttpMessageReaderFactory() {
    }

    @Override
    public IMessageReader createMessageReader() {
        return new HttpMessageReader();
    }
}
