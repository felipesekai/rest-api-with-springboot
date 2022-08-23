//package br.com.sekai.util
//
//import br.com.sekai.data.vo.v1.PersonVO
//import io.swagger.v3.oas.annotations.media.ArraySchema
//import io.swagger.v3.oas.annotations.media.Content
//import io.swagger.v3.oas.annotations.media.Schema
//import io.swagger.v3.oas.annotations.responses.ApiResponse
//
//object ApiResponseFunctions {
//
//    fun apiResponse(code: Int, isList: Boolean): ApiResponse {
//        if (code == 200 && isList) {
//            return ApiResponse(
//                description = "success",
//                responseCode = "200",
//                content = arrayOf(
//                    Content(array = ArraySchema(schema = Schema(implementation = PersonVO::class)))
//                )
//            )
//        } else if (code == 200) {
//            return ApiResponse(
//                description = "success",
//                responseCode = "200",
//                content = arrayOf(
//                    Content(schema = Schema(implementation = PersonVO::class))
//                )
//            )
//
//        } else if (code == 204) {
//            return ApiResponse(
//                description = "No content",
//                responseCode = "204",
//                content = arrayOf(
//                    Content(schema = Schema(implementation = Unit::class))
//                )
//            )
//        } else if (code == 400) {
//            return ApiResponse(
//                description = "Bad Request",
//                responseCode = "400",
//                content = arrayOf(
//                    Content(schema = Schema(implementation = Unit::class))
//                )
//            )
//        } else if (code == 401) {
//            return ApiResponse(
//                description = "Unauthorized",
//                responseCode = "401",
//                content = arrayOf(
//                    Content(schema = Schema(implementation = Unit::class))
//                )
//            )
//        } else if (code == 404) {
//            return ApiResponse(
//                description = "Not found",
//                responseCode = "404",
//                content = arrayOf(
//                    Content(schema = Schema(implementation = Unit::class))
//                )
//            )
//        } else {
//            return ApiResponse(
//                description = "Internal error",
//                responseCode = "500",
//                content = arrayOf(
//                    Content(schema = Schema(implementation = Unit::class))
//                )
//            )
//        }
//
//    }
//}