package com.wanzi.kotlin_functions

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

// https://www.jianshu.com/p/a7c4c2b0ef86 常用高阶函数 参考这里

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * 作用于集合对象，String等，用来迭代集合对象中的每个元素
     * 可以看作是for循环
     */
    fun forEach(view: View) {
        val array = arrayOf("a", "b", "c", "d")
        array.forEach {
            log(it)
        }
    }

    /**
     * 作用于集合对象，String等，用来迭代操作集合对象中的每个元素，并返回
     * 和forEach区别是有返回值
     * 可以看作for循环后返回一个集合
     */
    fun map(view: View) {
        val array = arrayOf("a", "b", "c", "d")
        array.map {
            "map:$it"
        }.forEach {
            log(it)
        }
    }

    /**
     * 作用于集合对象，String等，遍历目标集合中的每个元素，把元素作为入参执行传入的函数，返回一个R类型的可迭代对象（集合），然后将转换后集合一起加入到一个新的临时集合中，待遍历完毕后，将这个临时集合作为返回值返回
     */
    fun flatMap(view: View) {
        val array = arrayOf(1..10, 20..30, 50..80)
        array.flatMap {
            it
        }
                .map {
                    "No.$it"
                }
                .forEach {
                    log(it)
                }
    }

    /**
     * 作用于集合对象，String等，用来积累
     * 第二个例子中，acc是string，s是string
     */
    fun reduce(view: View) {
        val array = arrayOf("1", "2", "3", "4")
        val total = array.map { it.toInt() }.reduce { acc, i -> acc + i }
        log("total:$total") // 结果是 total:10

        val stringArray = arrayOf("Java", "Kotlin", "wanzi")
        val string = stringArray.reduce { acc, s -> "$acc,$s" }
        log("string:$string") // 结果是  string:Java,Kotlin,wanzi
    }

    /**
     * 和reduce类似，比reduce多了一个初始值
     * 第二个例子中，acc是int，s是string
     */
    fun fold(view: View) {
        val array = arrayOf("Java", "Kotlin", "wanzi")
        val string = array.fold("Title:") { acc, s ->
            "$acc,$s"
        }
        log("string:$string") // 结果是  string:Title:,Java,Kotlin,wanzi

        val stringLength = array.fold(0) { acc, s ->
            log("acc:$acc s:$s  s.length:${s.length}")
            acc + s.length
        }
        log("stringLength:$stringLength")
    }

    /**
     * 作用是迭代对象，将符合条件的元素保留存在临时集合中，迭代结束将临时集合返回。
     */
    fun filter(view: View) {
        val array = arrayOf("1", "2", "3", "4", "5")
        array.filter { it.toInt() % 2 == 0 }.forEach { log(it) }
    }

    /**
     * 作用是迭代集合，符合判断条件的保存起来，直到找到第一个不满足条件的或者迭代结束，最终把临时保存的集合返回。
     */
    fun takeWhile(view: View) {
        val array = arrayOf("1", "2", "3", "4", "5")
        array.takeWhile { it != "3" }.forEach { log(it) }
    }

    /**
     * 当takeIf内部条件满足时返回当前元素，不满足时返回null
     */
    fun takeIf(view: View) {
        val string = "abc".takeIf {
            it.length > 2
        }
        log("string:$string")
    }

    /**
     * 和takeIf相反
     */
    fun takeUnless(view: View) {
        val string = "abc".takeUnless {
            it.length > 2
        }
        log("string:$string")
    }


    // 还有关于 let、with、run、also、apply
    // apply和run基本可以满足大部分需求，至于apply和run的区别，apply返回对象，run返回最后一行或者不返回
    // https://blog.csdn.net/u013064109/article/details/78786646#6

    fun apply(view: View) {
        val array: ArrayList<String>? = null

        // 判断是否为null，为null不执行
        var list = array?.also {
            log("array为null") // 这里不会打印
        }
        log("list是否为空:${list == null}") // true

        list = ArrayList()
        list.also {
            it.add("a")
            it.add("b")
            it.add("c")
        }.forEach {
            log(it)
        }
    }

    fun run(view: View) {
        var array: ArrayList<String>? = null

        // 判断是否为null，为null不执行
        array?.run {
            log("array为null") // 这里不会打印
        }

        array = ArrayList()
        val d = array.run {
            add("a")  // this可以省略
            add("b")
            add("c")
            "d"
        }
        log("d:$d")
    }

    private fun log(message: String) {
        Log.i("wanzi", message)
    }
}
