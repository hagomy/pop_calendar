package com.pickth.haeun.popcalendar.model

/**
 * 연도, 월, 날짜를 편하게 다루기 위해 만든 클래스
 */
data class MyDate(var year: Int, var month: Int, var day: Int) {
    override fun equals(other: Any?): Boolean {
        return other is MyDate && other.year == year && other.month == month && other.day == day
    }
}