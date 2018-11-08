package com.example.serachwork.searchwordpuzzle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayout
import android.util.Log
import android.view.View
import android.widget.TextView
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    var gv : GridLayout? = null
    var word : String? = null
    var char : CharArray? = null
    var status: Int? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val arrayOfZeros = CharArray(8)
//        for (i in arrayOfZeros.indices) {
//            for (j in arrayOfZeros.indices) {
//                arrayOfZeros[j] = 'A'
//            }
//        }

        gv = findViewById<View>(R.id.gridLayout) as GridLayout
//        var row = CharArray()
//        var col = CharArray()
        word = "LOVE"
        char = word!!.toCharArray()
        Log.d("wordxxxx", char.toString())
        var datatable = Array(gv!!.rowCount) { CharArray(gv!!.columnCount) }
        var status = 1
        setWord(datatable)

    }

        fun setWord(datatable: Array<CharArray>) {
            var dataGrid  = Array(gv!!.rowCount) { CharArray(gv!!.columnCount) }
            dataGrid = datatable

            val randomRow = (0..7).shuffled().first()
            val randomCol = (0..7).shuffled().first()
            //dataGrid!![randomRow][randomCol] = char!![0]

            var randomType = (1..3).shuffled().first()
            Log.d("typerandom",randomType.toString())
            var index_row = 0
            var index_col = 0
            //randomType = 1
            if(randomType == 1) {
                        Log.d("resultxx-row", ((gv!!.rowCount).minus(randomRow) >= word!!.length).toString())
                        if ((gv!!.rowCount).minus(randomRow) >= word!!.length) {
                            for(i in randomRow..(gv!!.rowCount - 1)) {
                                dataGrid!![i][randomCol] = char!![index_row]
                                Log.d("char-row", char!![index_row].toString())
                                if (index_row < word!!.length-1) {
                                    index_row++
                                }else{
                                    char!![index_row] = ' '
                                }
                            }
                        } else {
                            Log.d("error-word", "randomtype 1 ")
                            //dataGrid = Array(gv!!.rowCount) { CharArray(gv!!.columnCount) }
                            setWord(datatable)
                        }
            }else if(randomType == 2){
                if ((gv!!.columnCount).minus(randomCol) >= word!!.length) {
                        for (j in randomCol..gv!!.columnCount - 1) {
                            Log.d("resultxx-col", (gv!!.columnCount - 1).minus(randomCol).toString() + "=" + word!!.length)
                            try {
                                dataGrid!![randomRow][j] = char!![index_col]
                                Log.d("char-col", char!![index_col].toString())
//                                var textView = TextView(this)
//                                var data = datatable[randomRow][j]
//                                textView.text = data.toString()
//                                gv.addView(textView)
//                                textView.setTextSize(20F)
//                                textView.setPadding(0, 10, 100, 10)
//                                gv.setPadding(10, 10, 10, 10)
                            } catch (e: Exception) {
                                Log.d("error", e.toString())
                            }
                            if(index_col < word!!.length-1) {
                                index_col++
                            }
                            else{
                                char!![index_col] = ' '
                            }
                    }
                } else if ((gv!!.columnCount).minus(randomCol) <= word!!.length) {
                    Log.d("typerandom-exception", "randomtype 2 ")
                    setWord(datatable)
                }
            }else if(randomType == 3){
                var index : Int = randomRow
                var randomType3 = (1..2).shuffled().first()
                if(randomType3 == 1){
                        var index_col = 0
                            if ((gv!!.columnCount).minus(randomCol) >= word!!.length && ((gv!!.rowCount).minus(randomRow) >= word!!.length)) {
                                for(col in randomCol..(gv!!.columnCount-1)) {
                                    try {
                                        dataGrid[index][col] = char!![index_col]
                                    } catch (e: Exception) {
                                        Log.d("error", e.toString())
                                    }
                                    if (index_col < word!!.length-1) {
                                        index_col++
                                    }else{
                                        char!![index_col] = ' '
                                    }
                                    index++
                                }
                            }else if((gv!!.columnCount).minus(randomCol) <= word!!.length || ((gv!!.rowCount).minus(randomRow) <= word!!.length)){
                                setWord(datatable)
                            }
                }else {
                    var index = randomRow
                    var index_col = 0
                    if ((gv!!.columnCount).minus(randomCol) >= word!!.length && ((randomRow).minus(0) >= word!!.length)) {
                        for (col in randomCol..(gv!!.columnCount - 1)) {
                            try {
                                dataGrid!![index][col] = char!![index_col]
                            } catch (e: Exception) {
                                Log.d("error", e.toString())
                            }
                            if (index_col < word!!.length-1) {
                                index_col++
                            }else{
                                char!![index_col] = ' '
                            }
                            index--
                        }

                    } else if ((gv!!.columnCount).minus(randomCol) <= word!!.length || ((randomRow).minus(0) <= word!!.length)) {
                        setWord(datatable)
                    }
                }
            }
            gv!!.removeAllViews()
            for (i in 0..gv!!.rowCount - 1) {
                for (j in 0..gv!!.columnCount - 1) {
                    //if(!datatable[i][j].equals(null)) {
                    var textView = TextView(this)
                    //var layout = LinearLayout(this)
                    //layout.setBackgroundColor(Color.RED)
                    val param = GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f))
                    textView.layoutParams = param
                    textView.text = datatable!![i][j].toString()
                    textView.id = i
                    textView.setBackgroundResource(R.drawable.border)

                    gv!!.addView(textView)
                    textView.setTextSize(20F)
                    textView.setPadding(20, 10, 40, 10)
                    gv!!.setPadding(10, 10, 10, 10)
                }
            }
            datatable

        }
    }
