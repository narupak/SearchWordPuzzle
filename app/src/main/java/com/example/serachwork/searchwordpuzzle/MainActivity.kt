package com.example.serachwork.searchwordpuzzle

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayout
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import android.view.Gravity


class MainActivity : AppCompatActivity(), View.OnTouchListener, View.OnClickListener {
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return false
    }

    override fun onClick(v: View?) {

    }

    var gv : GridLayout? = null
    var status: Int? = 0
    var datatable : Array<CharArray>? = null
    var statusClick = 0
    var matrix1 :  List<String>? = null
    var matrix2 :  List<String>? = null
    var row1 : Int? = null
    var row2 : Int? = null
    var col1 : Int? = null
    var col2 : Int? = null
    var textViews: ArrayList<TextView> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var gridsize = 8
        var wordList = ArrayList<Word>()
        val word7 = Word("THUNDER")
        val word1 = Word("DOG")
        val word2 = Word("APPLE")
        val word3 = Word("ORANGE")
        val word4 = Word("FISH")
        val word5 = Word("SNAKE")
        val word6 = Word("WATER")
        val word8 = Word("HUMAN")
        wordList.add(word7)
        wordList.add(word1)
        wordList.add(word2)
        wordList.add(word3)
        wordList.add(word4)
        wordList.add(word5)
        wordList.add(word6)
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
        datatable = Array(gridsize) { CharArray(gridsize) }
        for (rowChar in 0..(gridsize-1)) {
            for (colChar in 0..(gridsize-1)) {
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
            dataGridReturn = setWord(word,char,gridsize)
            Log.d("data","data")
        }
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        for (rowChar in 0..(gridsize-1)) {
            for (colChar in 0..(gridsize-1)) {
                if(dataGridReturn!![rowChar][colChar] == ' ') {
                    dataGridReturn!![rowChar][colChar] = chars[Math.floor(Math.random() * chars.length).toInt()]
                }
            }
        }
        show_table(dataGridReturn,gridsize,wordList)

    }

    fun show_table(
        dataGridReturn: Array<CharArray>?,
        gridsize: Int,
        wordList: ArrayList<Word>
    ) {
        //gv!!.removeAllViews()
        var searchWord : String? = ""
        var dataList = ArrayList<Char>()
        for (i in 0..gridsize - 1) {
            for (j in 0..gridsize - 1) {
                var textView = TextView(this)
                val param = GridLayout.LayoutParams(
                    GridLayout.spec(GridLayout.UNDEFINED, 1f),
                    GridLayout.spec(GridLayout.UNDEFINED, 1f)
                )
                dataList.add(dataGridReturn!![i][j])
                textView.layoutParams = param
                textView.text = dataGridReturn!![i][j].toString()
                textView.id = i
                textView.setBackgroundResource(R.drawable.border)
                textView.gravity = Gravity.CENTER
                textView.setTag(i.toString()+"+"+j)
                textView.setOnClickListener {
                    if(statusClick == 0){
                        var tag = textView.getTag() as String
                        //Log.d("Tagxxxxx",tag)
                        matrix1 = tag.split("+")
                        row1 = matrix1!![0].toInt()
                        col1 = matrix1!![1].toInt()
                        statusClick++
                    }else{
                        var tag = textView.getTag() as String
                        matrix2 = tag.split("+")
                        row2 = matrix2!![0].toInt()
                        col2 = matrix2!![1].toInt()
                        if(matrix1!![0].equals(matrix2!![0])){
                                var numtrue = 1
                                //var view = gv!!.getChildAt(row1!!) as View
                                //var column = view
                                //Log.d("viewxxxxx",view.toString())
                                for (col in col1!!..col2!!) {
                                    for(text in textViews!!){
                                        Log.d("textxxxxx",(text.getTag() == (row1!!.toString()+"+"+col.toString())).toString())
                                        if(text.getTag() == (row1!!.toString()+"+"+col.toString())) {
                                            text.setBackgroundColor(Color.RED)
                                        }
                                    }
//                                    for(data in wordList){
//                                        if(data.wordChar.contains(dataGridReturn[row1!!][col])){
//                                            Log.d("Wordtrue", dataGridReturn[row1!!][col].toString())
//                                            numtrue += 1
//                                            break
//                                        }else{
//                                            Log.d("Wordfalse", "false")
//                                        }
//                                        if(numtrue == data.wordChar.length){
//                                            Log.d("Wordxxxxx", (numtrue == data.wordChar.length).toString())
//                                            textView.setBackgroundColor(Color.RED)
//                                        }else{
//                                            Log.d("Wordxxxxx", (numtrue == data.wordChar.length).toString())
//                                        }
//                                    }
                                    //searchWord += dataGridReturn[row1!!][col]
                                }

                                //searchWord = ""
//                                for(data in wordList) {
//                                    Log.d("Wordxxxxx", searchWord.toString())
//                                }

                            Log.d("matrix",matrix1.toString()+" "+matrix2.toString())
                        }else if(matrix1!![1].equals(matrix2!![1])){
                            for(row in row1!!..row2!!){
                                for(text in textViews!!){
                                    Log.d("textxxxxx",(text.getTag() == (row!!.toString()+"+"+col2.toString())).toString())
                                    if(text.getTag() == (row!!.toString()+"+"+col2.toString())) {
                                        text.setBackgroundColor(Color.RED)
                                    }
                                }
                            }
                            Log.d("Wordxxxxx",searchWord.toString())
                        }else{
                            if(row1!! < row2!!){
                                var col = col1!!
                                for(row in row1!!..row2!!){
                                    for(text in textViews!!){
                                        Log.d("textxxxxx",(text.getTag() == (row.toString()+"+"+col.toString())).toString())
                                        if(text.getTag() == (row.toString()+"+"+col.toString())) {
                                            text.setBackgroundColor(Color.RED)
                                        }
                                    }
                                    col++
                                }
                                Log.d("Wordxxxxx",searchWord.toString())
                            }else{
                                var column = col1!!
                                for(row in row1!!.downTo(row2!!)){
                                    for(text in textViews!!){
                                        Log.d("textxxxxx",(text.getTag() == (row.toString()+"+"+column.toString())).toString())
                                        if(text.getTag() == (row.toString()+"+"+column.toString())) {
                                            text.setBackgroundColor(Color.RED)
                                        }
                                    }
                                    column++
                                }
                                Log.d("Wordxxxxx",searchWord.toString())
                            }
                        }
                        statusClick = 0
                    }
                    //matrix[0] = tag
                }
                textView.setTextSize(20F)
                textView.setPadding(20, 10, 40, 10)
                gv!!.setPadding(10, 10, 10, 10)
                //textViewList!!.add(textView)\
                textViews.add(textView)
                gv!!.addView(textView)
                gv!!.columnCount = gridsize
                gv!!.rowCount = gridsize
            }
        }

    }

        fun setWord(word: String, char: CharArray, gridsize: Int) : Array<CharArray>{
            var dataGrid  = Array(gridsize) { CharArray(gridsize) }
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
            var temp   = Array(gridsize) { CharArray(gridsize) }
            if(randomType == 1) {
                        Log.d("resultxx-row", ((gridsize.minus(randomRow) >= word.length).toString()))
                        if (gridsize.minus(randomRow) >= word.length) {
                            for(row in randomRow..(gridsize - 1)) {
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

                                setWord(word, char, gridsize)
                            }
                        } else {
                            Log.d("error-word", "randomtype 1 ")
                            //dataGrid = Array(gv!!.rowCount) { CharArray(gv!!.columnCount) }
                            setWord(word, char, gridsize)
                        }
            }else if(randomType == 2){
                if ((gridsize).minus(randomCol) >= word.length) {
                        for (col in randomCol..gridsize - 1) {
                            Log.d("resultxx-col", (gridsize - 1).minus(randomCol).toString() + "=" + word.length)
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

                            setWord(word, char, gridsize)
                        }
                } else {
                    setWord(word, char, gridsize)
                }
            }else if(randomType == 3){
                var index  = randomRow
                var randomType3 = (1..2).shuffled().first()
                //randomType3 = 1
                if(randomType3 == 1){
                        var index_col = 0
                            if ((gridsize).minus(randomCol) >= word.length && ((gridsize).minus(randomRow) >= word.length)) {
                                for(col in randomCol..(gridsize-1)) {
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

                                    var index_row = randomRow
                                    var index_col = randomCol

                                    for(addcol in randomCol..(word.length-1)) {
                                        dataGrid[index_row][index_col] = temp[index_row][index_col]
                                        index_col++
                                        index_row++

                                    }

                                    setWord(word, char, gridsize)
                                }
                            }else{
                                setWord(word, char, gridsize)
                            }
                }else {
                    var index_col = 0
                    if ((gridsize).minus(randomCol) >= word.length && (randomRow >= word.length)) {
                        for(col in randomCol..(gridsize-1)) {
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
//
                            setWord(word, char, gridsize)
                        }
                    }else{
                        setWord(word, char, gridsize)
                    }
                }
            }
            return dataGrid
        }
    }
