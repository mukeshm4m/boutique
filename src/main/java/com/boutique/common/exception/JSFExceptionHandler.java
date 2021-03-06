package com.boutique.common.exception;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;

public class JSFExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    public JSFExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void handle() throws FacesException {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        for (Iterator<ExceptionQueuedEvent> iter = getUnhandledExceptionQueuedEvents().iterator(); iter.hasNext();) {
            Throwable exception = iter.next().getContext().getException();

            if (exception instanceof ViewExpiredException) {
                facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "toViewExpirePage");
                facesContext.renderResponse();
                iter.remove();
            }
        }

        getWrapped().handle();
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }
}
