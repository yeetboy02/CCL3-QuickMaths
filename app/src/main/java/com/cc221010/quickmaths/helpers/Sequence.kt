package com.cc221010.quickmaths.helpers

@OptIn(ExperimentalStdlibApi::class)
class NumberSequence(
    override var numbers:List<Int> = emptyList(),
    override val operators:List<Int> = (0..getRandInt(0, 2)).map { getRandInt(0, 2) },
    var operatorNumbers:List<Int> = emptyList(),
    override var length:Int = 0,
    override var result:Int = 0,
    override var asString:String = "",
    override var maxPoints:Int = 1000,
    override val minPoints:Int = 0
):Question {
    init {
        var output:Int = operators.size * 200;
        maxPoints += output;
    }
    init {
        var output:Int = 0;
        if (operators.size * 2 > 4) {
            output = getRandInt(4, operators.size * 2);
        }
        else {
            output = getRandInt(operators.size * 2, 4);
        }
        length = output;
    }

    init {
        var output:List<Int> = emptyList();
        for (i in 0..<operators.size) {
            if (operators[i] == 2) {
                output += getRandInt(2, 4)
            }
            else {
                output += getRandInt(1, 20);
            }
        }
        operatorNumbers = output;
    }

    init {
        var output:List<Int> = listOf(getRandInt(0, 100));


        for (i in 0.. length) {
            if (i == length) {
                result = when (operators[i % operators.size]) {
                    0 -> output[i] + operatorNumbers[i % operators.size];
                    1 -> output[i] - operatorNumbers[i % operators.size];
                    2 -> output[i] * operatorNumbers[i % operators.size];
                    else -> output[i];
                }
                break;
            }
            output += when (operators[i % operators.size]) {
                0 -> output[i] + operatorNumbers[i % operators.size];
                1 -> output[i] - operatorNumbers[i % operators.size];
                2 -> output[i] * operatorNumbers[i % operators.size];
                else -> output[i];
            }
        }
        numbers = output;
    }

    init {
        var firstNumber:Int = numbers[0];
        var output:String = "$firstNumber";

        for (i in 1..<numbers.size) {
            val currNumber:Int = numbers[i];
            output = "$output ... $currNumber";
        }

        output += " ... ___";
        asString = output;
    }
}