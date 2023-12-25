ext.implementationCustom = [
    retrofit : {
    implementation libraries . retrofitBase
            implementation libraries . retrofitRxAdapter
            implementation libraries . retrofitMoshiConverter
},
glide : {
    implementation libraries . glideBase
            annotationProcessor libraries . glideAnnotations
}
]