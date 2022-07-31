package br.com.sekai.exceptions

import java.lang.Exception
import java.lang.RuntimeException

class UnsupportedMAthOperationException (
    exception: String?
): RuntimeException(exception)
