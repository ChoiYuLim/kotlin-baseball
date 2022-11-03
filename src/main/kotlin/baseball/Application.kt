package baseball

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms
import java.lang.IllegalArgumentException

var randomNum = 0

fun main() {
    print("숫자 야구 게임을 시작합니다.")
    randomNum = makeRandomNumber()
    while (true) {
        inputAndCheckNum()
    }
}

private fun makeRandomNumber(): Int {
    val computer = mutableListOf<Int>()
    while (computer.size < 3) {
        val randomNumber = Randoms.pickNumberInRange(1, 9)
        if (!computer.contains(randomNumber)) {
            computer.add(randomNumber)
        }
    }
    return computer.joinToString("").toInt()
}

private fun inputAndCheckNum() {
    val inputNum = getInputNumber()
    if (isRightInput(inputNum)) {
        // TODO: 다음 단계 구현
    } else throw IllegalArgumentException("1~9 사이의 서로 다른 세자리 수를 입력하세요")
}

private fun getInputNumber(): Int {
    print("숫자를 입력해주세요 : ")
    return Console.readLine().toInt()
}

private fun isRightInput(num: Int) = isThreeDigitNum(num) && !existZero(num) && isDifferentNum(num)

private fun isThreeDigitNum(num: Int) = num in (100..999)

private fun existZero(num: Int) = num.toString().contains('0')

private fun isDifferentNum(num: Int): Boolean {
    val set = mutableSetOf<Char>()
    num.toString().map {
        set.add(it)
    }
    return set.size == 3
}

private fun compareNumber(inputNum: Int): Pair<Int, Int> {
    var ball = 0
    var strike = 0

    randomNum.toString().forEachIndexed { i, digitOne ->
        inputNum.toString().forEachIndexed EXIT@{ j, digitTwo ->
            if (i == j && digitOne == digitTwo) {
                strike += 1
                return@EXIT
            } else if (i != j && digitOne == digitTwo) {
                ball += 1
                return@EXIT
            }
        }
    }

    return Pair(ball, strike)
}