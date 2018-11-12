package com.example.serachwork.searchwordpuzzle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayout
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


class MainActivity : AppCompatActivity(), View.OnTouchListener, View.OnClickListener, MyView.OnToggledListener {

    var myViews: Array<MyView>? = null
    var gv : GridLayout? = null
    var status: Int? = 0
    var dataList = ArrayList<Array<CharArray>>()
    var datatable : Array<CharArray>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var wordList = ArrayList<Word>()
        val word1 = Word("DOG")
        val word2 = Word("APPLE")
        val word3 = Word("ORANGE")
        val word4 = Word("FISH")
        val word5 = Word("SNAKE")
        val word6 = Word("WATER")
        val word7 = Word("THUNDER")
        val word8 = Word("HUMAN")
        wordList.add(word1)
        wordList.add(word2)
        wordList.add(word3)
        wordList.add(word4)
        wordList.add(word5)
        wordList.add(word6)
        wordList.add(word7)
        wordList.add(word8)



        for(data in wordList){
            var textView  = TextView(this)
            var word = data.wordChar
            textView.text = word
            textView.setTextSize(16F)
            textView.setPadding(20, 10, 40, 10)
            gridLayouttextView.setPadding(10,10,10,10)
            gridLayouttextView.addView(textView)

        }

        gv = findViewById<View>(R.id.gridLayout) as GridLayout
        datatable = Array(gv!!.rowCount) { CharArray(gv!!.columnCount) }
        for (rowChar in 0..(gv!!.rowCount-1)) {
            for (colChar in 0..(gv!!.columnCount-1)) {
                datatable!![rowChar][colChar] = ' '
            }
        }

        var dataGridReturn : Array<CharArray>? = null
        for (wotdString in wordList) {
//          var row = CharArray()
//          var col = CharArray()
            var word = wotdString.wordChar
            var char = wotdString.wordChar.toCharArray()
            Log.d("wordxxxx", char.toString())
            dataGridReturn = setWord(word,char)
            Log.d("data","data")
        }
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        for (rowChar in 0..(gv!!.rowCount-1)) {
            for (colChar in 0..(gv!!.columnCount-1)) {
                if(dataGridReturn!![rowChar][colChar] == ' ') {
                    dataGridReturn!![rowChar][colChar] = chars[Math.floor(Math.random() * chars.length).toInt()]
                }
            }
        }
        show_table(dataGridReturn)

    }

    fun show_table(dataGridReturn: Array<CharArray>?) {
        gv!!.removeAllViews()
        for (i in 0..gv!!.rowCount - 1) {
            for (j in 0..gv!!.columnCount - 1) {
                //if(!datatable[i][j].equals(null)) {
                var textView = TextView(this)
                //var layout = LinearLayout(this)
                //layout.setBackgroundColor(Color.RED)
                val param = GridLayout.LayoutParams(
                    GridLayout.spec(GridLayout.UNDEFINED, 1f),
                    GridLayout.spec(GridLayout.UNDEFINED, 1f)
                )
                textView.layoutParams = param
                textView.text = dataGridReturn!![i][j].toString()
                textView.id = i
                textView.setBackgroundResource(R.drawable.border)
                textView.gravity = Gravity.CENTER
                var canvass = Canvas()
                //layout.addView(textView)
                //layout.addView(canvass)
                var myView = MyView(this,i,j)
                myView.setOnToggledListener(this)
                textView.setOnTouchListener(this)
                textView.setOnClickListener(this)
                textView.setTextSize(20F)
                textView.setPadding(20, 10, 40, 10)
                gv!!.setPadding(10, 10, 10, 10)
                gv!!.addView(textView)
            }
        }

    }

    override fun OnToggled(v: MyView, touchOn: Boolean) {

    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        Log.d("xxxx","touch")
        //v.findViewById<View>(R.id.i)
        return true
    }

    override fun onClick(v: View?) {
        Log.d("xxxx","click")
    }



        fun setWord(word: String, char: CharArray) : Array<CharArray>{
            var dataGrid  = Array(gv!!.rowCount) { CharArray(gv!!.columnCount) }
            dataGrid = datatable!!

            val randomRow = (0..7).shuffled().first()
            val randomCol = (0..7).shuffled().first()
            //dataGrid!![randomRow][randomCol] = char!![0]

            var randomType = (1..3).shuffled().first()
            Log.d("typerandom",randomType.toString())
            var index_row = 0
            var index_col = 0
            var status_recursive = 0
            //randomType = 3
            var temp   = Array(gv!!.rowCount) { CharArray(gv!!.columnCount) }
            if(randomType == 1) {
                        Log.d("resultxx-row", ((gv!!.rowCount).minus(randomRow) >= word.length).toString())
                        if ((gv!!.rowCount).minus(randomRow) >= word.length) {
                            for(row in randomRow..(gv!!.rowCount - 1)) {
                                if (index_row < word.length) {
                                    temp!![row][randomCol] = dataGrid!![row][randomCol]
                                    Log.d("xxxxxxxxxx",dataGrid[row][randomCol].toString())
                                    if(dataGrid[row][randomCol] == ' '){
                                        dataGrid!![row][randomCol] = char[index_row]
                                    }else {
                                        if(char[index_row] == dataGrid[row][randomCol]){
                                            //index_row++
                                        }else{
                                            status_recursive = 1
                                        }
                                    }

                                    index_row++
                                } else {
                                }
                            }
                            if(status_recursive == 1) {

                                for (delrow in ((randomRow + word.length)-1).downTo(randomRow)){
                                    dataGrid[delrow][randomCol] = ' '
                                }
                                for (addrow in ((randomRow + word.length)-1).downTo(randomRow)){
                                    dataGrid[addrow][randomCol] = temp!![addrow][randomCol]
                                }

                                setWord(word,char)
                            }
                        } else {
                            Log.d("error-word", "randomtype 1 ")
                            //dataGrid = Array(gv!!.rowCount) { CharArray(gv!!.columnCount) }
                            setWord(word, char)
                        }
            }else if(randomType == 2){
                if ((gv!!.columnCount).minus(randomCol) >= word.length) {
                        for (col in randomCol..gv!!.columnCount - 1) {
                            Log.d("resultxx-col", (gv!!.columnCount - 1).minus(randomCol).toString() + "=" + word.length)
                            if (index_col < word.length) {
                                temp!![randomRow][col] = dataGrid!![randomRow][col]
                                try {
                                    if(dataGrid[randomRow][col] == ' ') {
                                        dataGrid!![randomRow][col] = char[index_col]
                                    }else{
                                        if(char[index_col] == dataGrid[randomRow][index_col]){

                                        }else{
                                            status_recursive = 1
                                        }
                                    }
                                    Log.d("char-col", char[index_col].toString())
                                } catch (e: Exception) {
                                    Log.d("error", e.toString())
                                }
                                index_col++
                            }
                        }
                        if(status_recursive == 1) {

                            for (delcol in ((randomCol + word.length)-1).downTo(randomCol)){
                                dataGrid[randomRow][delcol] = ' '
                            }
                            for (addcol in ((randomCol + word.length)-1).downTo(randomCol)){
                                dataGrid[randomRow][addcol] = temp!![randomRow][addcol]
                            }

                            setWord(word,char)
                        }
                } else {
                    setWord(word, char)
                }
            }else if(randomType == 3){
                var index  = randomRow
                var randomType3 = (1..2).shuffled().first()
                //randomType3 = 1
                if(randomType3 == 1){
                        var index_col = 0
                            if ((gv!!.columnCount).minus(randomCol) >= word.length && ((gv!!.rowCount).minus(randomRow) >= word.length)) {
                                for(col in randomCol..(gv!!.columnCount-1)) {
                                    if (index_col < word.length) {
                                        temp!![index][col] = dataGrid!![index][col]
                                            if (dataGrid[index][col] == ' ') {
                                                dataGrid[index][col] = char[index_col]
                                            } else {
                                                if(char[index_col] == dataGrid[index][index_col]){

                                                }else{
                                                    status_recursive = 1
                                                }
                                            }
                                        index_col++
                                    }
                                    index++
                                }
                                if(status_recursive == 1) {


//                                    var index_row_del_cross = (randomRow+(word.length)+1)
//                                    for(addcol in ((randomCol + word.length)-1).downTo(randomCol)) {
//                                        if (index_row_del_cross < randomRow - 1) {
//                                            try {
//                                                dataGrid[index_row_del_cross][addcol] = temp[index_row_del_cross][addcol]
//                                                index_row_del_cross--
//                                            } catch (e: Exception) {
//
//                                            }
//                                        }
//                                    }

                                    var index_row = randomRow
                                    var index_col = randomCol

                                    for(addcol in randomCol..(word.length-1)) {
                                        dataGrid[index_row][index_col] = temp[index_row][index_col]
                                        index_col++
                                        index_row++

                                    }
                                    //var index_del_cross = (randomRow - word.length)
                                    //for (delrow in ((randomRow + word.length)-1).downTo(randomRow)){
//                                        for(delcol in ((randomCol + word.length)-1).downTo(randomCol)){
//                                            if(index_del_cross > randomRow) {
//                                                try {
//                                                    dataGrid[index_del_cross][delcol] = ' '
//                                                    index_del_cross++
//                                                }catch(e : Exception){
//
//                                                }
//                                            }
//
//                                        }
                                    //}
                                    //for (addrow in ((randomRow + word.length)-1).downTo(randomRow)){
//                                    var index_add_cross = (randomRow - word.length)+1
//                                        for(addcol in ((randomCol + word.length)-1).downTo(randomCol)){
//                                            if(index_add_cross > randomRow-1) {
//                                                try {
//                                                    dataGrid[index_add_cross][addcol] = temp[index_add_cross][addcol]
//                                                    index_add_cross++
//                                                }catch(e:Exception){
//
//                                                }
//                                            }
//                                        }
                                   // }

                                    setWord(word,char)
                                }
                            }else{
                                setWord(word, char)
                            }
                }else {
                    var index_col = 0
                    if ((gv!!.columnCount).minus(randomCol) >= word.length && (randomRow >= word.length)) {
                        for(col in randomCol..(gv!!.columnCount-1)) {
                            if(index >= 0) {
                                if (index_col < word.length) {
                                    temp!![index][col] = dataGrid!![index][col]
                                    if (dataGrid[index][col] == ' ') {
                                        dataGrid[index][col] = char[index_col]
                                    } else {
                                        if (char[index_col] == dataGrid[index][index_col]) {

                                        } else {
                                            status_recursive = 1
                                        }
                                    }
                                    index_col++
                                }
                                index--
                            }
                        }
                        if(status_recursive == 1) {
//
                            var index_row_del_cross = (randomRow-(word.length)+1)
                            for(addcol in ((randomCol + word.length)-1).downTo(randomCol)) {
                                if (index_row_del_cross < randomRow + 1) {
                                    try {
                                        dataGrid[index_row_del_cross][addcol] = temp[index_row_del_cross][addcol]
                                        index_row_del_cross++
                                    } catch (e: Exception) {

                                    }
                                }
                            }
                            //temp = Array(gv!!.rowCount) { CharArray(gv!!.columnCount) }
//
                            setWord(word,char)
                        }
                    }else{
                        setWord(word, char)
                    }
                }
            }
            return dataGrid
        }
    }

class Canvass(context: Context) : View(context) {

    override fun onDraw(canvas: Canvas) {
        canvas.drawRGB(255, 255, 0)
        val width = getWidth()
        val paint = Paint()
        paint.setARGB(255, 255, 0, 0)
        canvas.drawLine(0f, 30f, width.toFloat(), 30f, paint)
        paint.setStrokeWidth(4f)
        canvas.drawLine(0f, 60f, width.toFloat(), 60f, paint)
    }
}
