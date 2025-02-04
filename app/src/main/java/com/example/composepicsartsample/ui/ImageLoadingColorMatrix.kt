package com.example.composepicsartsample.ui

import android.graphics.ColorMatrix

/**
 * An extension to [ColorMatrix] which implements the Material Design image loading pattern.
 *
 * @see https://material.io/archive/guidelines/patterns/loading-images.html
 */
class ImageLoadingColorMatrix(
    saturation: Float = 1f,
    alpha: Float = 1f,
    brightness: Float = 1f
) : ColorMatrix() {
    private val elements = FloatArray(20)

    var saturationFraction = 1f
        set(value) {
            if (value != field) {
                System.arraycopy(array, 0, elements, 0, 20)

                // Taken from ColorMatrix.setSaturation. We can't use that though since it
                // resets the matrix before applying the values
                val invSat = 1 - value
                val r = 0.213f * invSat
                val g = 0.715f * invSat
                val b = 0.072f * invSat

                elements[0] = r + value
                elements[1] = g
                elements[2] = b
                elements[5] = r
                elements[6] = g + value
                elements[7] = b
                elements[10] = r
                elements[11] = g
                elements[12] = b + value

                set(elements)
            }
            field = value
        }

    var alphaFraction = 1f
        set(value) {
            if (value != field) {
                System.arraycopy(array, 0, elements, 0, 20)
                elements[18] = value
                set(elements)
            }
            field = value
        }

    var brightnessFraction = 1f
        set(value) {
            if (value != field) {
                System.arraycopy(array, 0, elements, 0, 20)

                // We substract to make the picture look darker, it will automatically clamp
                val darkening = (1 - value) * 255
                elements[4] = -darkening
                elements[9] = -darkening
                elements[14] = -darkening

                set(elements)
            }
            field = value
        }

    init {
        saturationFraction = saturation
        alphaFraction = alpha
        brightnessFraction = brightness
    }
}