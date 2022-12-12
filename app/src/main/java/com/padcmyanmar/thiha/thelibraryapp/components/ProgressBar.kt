package com.padcmyanmar.thiha.thelibraryapp.components

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.withStyledAttributes
import com.padcmyanmar.thiha.thelibraryapp.R
import kotlin.math.roundToInt

class ProgressBar @JvmOverloads constructor(
     context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
 ) : View(context, attrs, defStyleAttr) {

     private var min = 0
     private var max = 100
     private var progress = 0
     private var progressBarThickness = 4.0f
     private var progressBarColor = Color.BLACK

     private var inactivePaint = Paint(Paint.ANTI_ALIAS_FLAG)
     private var activePaint = Paint(Paint.ANTI_ALIAS_FLAG)

     var path = Path()


     init {
         context.withStyledAttributes(attrs, R.styleable.ProgressBar) {
             min = getInt(R.styleable.ProgressBar_minLineValue, min)
             max = getInt(R.styleable.ProgressBar_maxLineValue, max)
             progress = getInt(R.styleable.ProgressBar_lineProgressValue, progress)
             progressBarThickness = getDimension(
                 R.styleable.ProgressBar_lineProgressBarThickness,
                 progressBarThickness
             )
             progressBarColor =
                 getColor(R.styleable.ProgressBar_lineProgressBarColor, progressBarColor)
         }

//        inactivePaint.color = calculateInactiveColor(progressBarColor, 0.3f)
         inactivePaint.color = context.getColor(R.color.grey)
         inactivePaint.strokeWidth = progressBarThickness
         inactivePaint.strokeCap = Paint.Cap.ROUND

         activePaint.color = progressBarColor
         activePaint.strokeWidth = progressBarThickness
         activePaint.strokeCap = Paint.Cap.ROUND

     }

     override fun onDraw(canvas: Canvas?) {
         super.onDraw(canvas)

         canvas?.drawLine(0f + progressBarThickness, height/2f, width.toFloat() - progressBarThickness, height/2f, inactivePaint)

         val progressValue = ((width * progress) / max).toFloat()
         Log.d("progress",progress.toString())
         canvas?.drawLine(0f + progressBarThickness, height/2f, progressValue, height/2f, activePaint)



         canvas?.clipPath(path)


     }

     override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
         val height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec)
         val width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec)
         setMeasuredDimension(width, height)
     }

     private fun calculateInactiveColor(color: Int, factor: Float) : Int {
         val alpha = (Color.alpha(color) * factor).roundToInt()
         val red = Color.red(color)
         val green = Color.green(color)
         val blue = Color.blue(color)

         return Color.argb(alpha, red, green, blue)
     }

     fun setProgress(value: Int){
         progress = value
         invalidate()
     }































 }
//class ProgressBar(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
//    var size = 0
//    var paint = Paint()
//    var path = Path()
//    var oval = RectF()
//    var progress=0
//    var borderWidth = 10f
//    var progressColor=0
//    var progressBarColor=0
//    init {
//        context?.withStyledAttributes(attrs, R.styleable.ProgressBar){
//            progress=getInt(R.styleable.ProgressBar_progressBarProgress,5)
//            progressBarColor=getColor(R.styleable.ProgressBar_progressBarBackgroundColor,0)
//            progressColor=getColor(R.styleable.ProgressBar_progressBarColor,0)
//            borderWidth=getDimension(R.styleable.ProgressBar_progressBarHeight,borderWidth)
//
//
//
//        }
//    }
//    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//        paint.color = Color.parseColor("#f58868")
//        paint.style = Paint.Style.FILL
////        paint.textSize = size * 0.34f
//        paint.strokeWidth = borderWidth
//        oval.set(
//            borderWidth / 2,
//            borderWidth / 2,
//            size - borderWidth / 2f,
//            size - borderWidth / 2
//        )
//        var rectangle = RectF(0f, 0f, size.toFloat(), borderWidth/2)
//        path.addRoundRect(rectangle, 20f, 20f, Path.Direction.CCW)
//        canvas?.clipPath(path)
//        paint.color = progressBarColor
//        canvas?.drawLine(size * 0.7f, 0f, size * 1f, 0f, paint)
//        paint.color = progressColor
//        val newRectangle = RectF(0f, 0f, size * 0.5f, borderWidth/2)
//        canvas?.drawRoundRect(newRectangle, 20f, 20f, paint)
//
//
//    }
//
//    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
//        super.onSizeChanged(w, h, oldw, oldh)
//        size = w
//    }
//
//
//}