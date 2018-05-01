package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return if (year != other.year)
            year.compareTo(other.year)
        else if (month != other.month)
            month.compareTo(other.month)
        else
            dayOfMonth.compareTo(other.dayOfMonth)
    }

    operator fun plus(interval: MultipleTimeInterval): MyDate {
        return this.addTimeIntervals(interval.timerInterval, interval.count)
    }

    operator fun plus(interval: TimeInterval): MyDate {
        return this.addTimeIntervals(interval, 1)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(count: Int): MultipleTimeInterval {
        return MultipleTimeInterval(this, count)
    }
}

class MultipleTimeInterval(val timerInterval: TimeInterval, val count: Int) {

}

class DateRange(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object: Iterator<MyDate> {
            var nextDay = start
            override fun next(): MyDate {
                val curDate = nextDay
                nextDay = curDate.nextDay()
                return curDate
            }

            override fun hasNext(): Boolean {
                return endInclusive >= nextDay
            }

        }
    }
}
