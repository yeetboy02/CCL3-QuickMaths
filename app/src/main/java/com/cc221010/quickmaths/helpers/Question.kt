package com.cc221010.quickmaths.helpers

interface Question {
    val length:Int;
    val numbers:List<Int>;
    val operators:List<Int>;
    val result:Int;
    val asString:String;
    val maxPoints:Int;
    val minPoints:Int;
}