package cn.itcast.b2c.gciantispider.exception;

import org.apache.log4j.Logger;
/**
 * 业务层的异常处理。
 *
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 7383145226051022473L;
    
    private static final Logger log = Logger.getLogger(ServiceException.class);

    public ServiceException() {

    }

    public ServiceException(String message) {
        super(message);
        log.error(message);
    }

    public ServiceException(Exception e) {
        super(e);
        log.error(e);
    }
}
