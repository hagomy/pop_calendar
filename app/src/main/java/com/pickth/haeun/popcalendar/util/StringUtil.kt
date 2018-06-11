package com.pickth.haeun.popcalendar.util

import com.pickth.haeun.popcalendar.model.MyDate
import java.text.SimpleDateFormat
import java.util.*

object StringUtil {
    fun getYear(): Int = Calendar.getInstance().get(Calendar.YEAR)

    fun getMonth(): Int = Calendar.getInstance().get(Calendar.MONTH)+1

    fun getDay(): Int = Calendar.getInstance().get(Calendar.DATE)

    fun getCurrendMyDate(): MyDate = MyDate(getYear(), getMonth(), getDay())

    fun getCurrentDay(): String = SimpleDateFormat("yyyy:MM:dd")
            .format(Date(System.currentTimeMillis()))

    fun formatDayToString(day: String): String {
        var result = (System.currentTimeMillis() - SimpleDateFormat("yyyy:MM:dd")
                .parse(day)
                .time) / 1000

        // 초
        result /= 60
        // 분
        result /= 60
        // 시
        result /= 24

        return result.toString()
//        if(result.toInt() == 0) {
//            return "오늘"
//        }
//        return "${result}일 전"
    }
}