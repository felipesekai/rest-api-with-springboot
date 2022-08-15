package br.com.sekai.exceptions.handler

import br.com.sekai.exceptions.ExceptionsResponse
import br.com.sekai.exceptions.RequiredObjectIsNullException
import br.com.sekai.exceptions.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception
import java.util.*

@ControllerAdvice
@RestController
class CustomizedResponseEntityExceptionHandler : ResponseEntityExceptionHandler(){

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(e: Exception, request: WebRequest)
    : ResponseEntity<ExceptionsResponse> {
        val exceptionsResponse = ExceptionsResponse(
            Date(),
            e.message,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionsResponse>(exceptionsResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(e: Exception, request: WebRequest)
    : ResponseEntity<ExceptionsResponse> {
        val exceptionsResponse = ExceptionsResponse(
            Date(),
            e.message,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionsResponse>(exceptionsResponse, HttpStatus.NOT_FOUND)
    }
    @ExceptionHandler(RequiredObjectIsNullException::class)
    fun handleBadRequestException(e: Exception, request: WebRequest)
    : ResponseEntity<ExceptionsResponse> {
        val exceptionsResponse = ExceptionsResponse(
            Date(),
            e.message,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionsResponse>(exceptionsResponse, HttpStatus.BAD_REQUEST)
    }
}
