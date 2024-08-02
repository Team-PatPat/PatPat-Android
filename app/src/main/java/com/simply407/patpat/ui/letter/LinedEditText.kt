package com.simply407.patpat.ui.letter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat

class LinedEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {
    private val paint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        color = 0xFF000000.toInt() // 기본 밑줄 색상 (검정색)
        strokeWidth = 2f // 밑줄 두께
    }

    private val lineSpacingExtra = 4f // 밑줄과 텍스트 사이의 간격 (dp 단위)

    fun setLineColor(colorResId: Int) {
        val color = ContextCompat.getColor(context, colorResId)
        paint.color = color
        invalidate() // 뷰를 다시 그리도록 요청
    }

    override fun onDraw(canvas: Canvas) {
        val lineCount = layout.lineCount
        val lineHeight = lineHeight
        val lineSpacingPx = lineSpacingExtra * resources.displayMetrics.density // dp를 px로 변환

        for (i in 0 until lineCount) {
            val baseline = getLineBounds(i, null)
            canvas.drawLine(
                paddingLeft.toFloat(),
                (baseline + lineSpacingPx + 1).toFloat(),
                (width - paddingRight).toFloat(),
                (baseline + lineSpacingPx + 1).toFloat(),
                paint
            )
        }

        super.onDraw(canvas)
    }
}

