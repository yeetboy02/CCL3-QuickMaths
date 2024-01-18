package com.cc221010.quickmaths.helpers

@OptIn(ExperimentalStdlibApi::class)
class Calculation(
    override val length:Int = getRandInt(2, 4),
    override var numbers:List<Int> = emptyList<Int>(),
    override val operators:List<Int> = (0..<length).map { getRandInt(0, 2) },
    override var result:Int = 0,
    override var asString:String = "",
    override val points:Int = 1000
):Question {

    init {
        var output:List<Int> = listOf(getRandInt(0, 100));
        for (i in 0..<length) {
            if (operators[i] == 2) {
                output += getRandInt(0, 10);
            }
            else {
                output += getRandInt(0, 100);
            }
        }
        numbers = output;
    }

    init {
        var output:Int = 0;
        var numberStack:MutableList<Int> = numbers.toMutableList();
        var operatorStack:MutableList<Int> = operators.toMutableList();
        var multFinished:Boolean = false;
        while (!multFinished) {
            for (i in 0..<operatorStack.size) {
                if (operatorStack[i] == 2) {
                    numberStack[i] = numberStack[i] * numberStack[i + 1];
                    numberStack.removeAt(i + 1);
                    operatorStack.removeAt(i);
                    if (i == operatorStack.size - 1) {
                        multFinished = true;
                    }
                    break;
                }
                if (i == operatorStack.size - 1) {
                    multFinished = true;
                }
            }
        }
        output = numberStack[0];
        for (k in 0..<operatorStack.size) {
            output = when (operatorStack[k]) {
                0 -> output + numberStack[k + 1];
                1 -> output - numberStack[k + 1];
                else -> output;
            }
        }
        result = output;
    }

    init {
        var output:String = numbers[0].toString();
        for (i in 0..<operators.size) {
            val currOperator:String = when(operators[i]){
                0 -> "+";
                1 -> "-";
                2 -> "*";
                else -> "";
            }
            val currNumber:String = numbers[i + 1].toString();
            output = "$output $currOperator $currNumber";
        }
        asString = "$output =";
    }
}