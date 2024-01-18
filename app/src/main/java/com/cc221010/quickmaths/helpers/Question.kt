package com.cc221010.quickmaths.helpers

abstract class Question {
    abstract val length:Int;
    abstract val numbers:List<Int>;
    abstract val operators:List<Int>;
    abstract val result:Int;
    abstract val asString:String;
}