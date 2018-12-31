package jmh.benchmarks

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder
import org.openjdk.jmh.runner.options.TimeValue
import java.math.BigDecimal
import java.util.concurrent.TimeUnit

/**
 * Created by tony on 2018-12-10.
 */
@BenchmarkMode(Mode.AverageTime) // 基准测试的模式，采用整体吞吐量的模式
@OutputTimeUnit(TimeUnit.MICROSECONDS) // 基准测试结果的时间类型
@State(Scope.Thread)
open class KotlinBasicBenchmark {

    @Benchmark
    fun doubleEqual() {
        val b = "0.00".toDouble() == 0.0

    }

    @Benchmark
    fun doubleCompare() {
        val b = "0.00".toDouble().compareTo(0) == 0
    }

    @Benchmark
    fun double2BigDecialCompare() {
        val b = BigDecimal("0.00".toDouble()).compareTo(BigDecimal.ZERO) == 0
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val options = OptionsBuilder()
                    .include(KotlinBasicBenchmark::class.java.simpleName)
                    .forks(2) //进行 fork 的次数。如果 fork 数是2的话，则 JMH 会 fork 出两个进程来进行测试
                    .warmupIterations(2) //预热的迭代次数
                    .warmupTime(TimeValue.valueOf("3"))
                    .measurementIterations(5) //实际测量的迭代次数
                    .measurementTime(TimeValue.valueOf("3"))
                    .build()
            Runner(options).run()
        }
    }
}




