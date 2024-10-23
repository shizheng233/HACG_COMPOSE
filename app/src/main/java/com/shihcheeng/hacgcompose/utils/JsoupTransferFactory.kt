package com.shihcheeng.hacgcompose.utils

import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.Converter
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import java.lang.reflect.Type

class JsoupTransferFactory : Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        return JsoupConverter
    }

    private object JsoupConverter : Converter<ResponseBody, Document> {
        override fun convert(p0: ResponseBody): Document {
            return Jsoup.parse(p0.string())
        }

    }

}