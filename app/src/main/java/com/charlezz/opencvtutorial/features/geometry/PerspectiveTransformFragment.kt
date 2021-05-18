package com.charlezz.opencvtutorial.features.geometry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.charlezz.opencvtutorial.BitmapUtil
import com.charlezz.opencvtutorial.databinding.FragmentPerspectiveTransformBinding
import com.charlezz.opencvtutorial.R
import dagger.hilt.android.AndroidEntryPoint
import org.opencv.android.Utils
import org.opencv.core.*
import org.opencv.imgproc.Imgproc
import javax.inject.Inject

@AndroidEntryPoint
class PerspectiveTransformFragment : Fragment() {

    private var _binding: FragmentPerspectiveTransformBinding? = null
    private val binding: FragmentPerspectiveTransformBinding get() = _binding!!

    @Inject
    lateinit var bitmapUtil: BitmapUtil

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPerspectiveTransformBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 일반적인 명함 사이즈
        val width = 388.0
        val height = 600.0

        val src = Utils.loadResource(requireContext(), R.drawable.business_card)
        Imgproc.resize(src, src, Size(600.0, 600.0))

        val pt1 = Point(40.0, 100.0)
        val pt2 = Point(345.0, 35.0)
        val pt3 = Point(183.0, 575.0)
        val pt4 = Point(584.0, 445.0)

        Imgproc.circle(src, pt1, 10, Scalar(0.0, 0.0, 255.0), -1)
        Imgproc.circle(src, pt2, 10, Scalar(0.0, 255.0, 0.0), -1)
        Imgproc.circle(src, pt3, 10, Scalar(255.0, 0.0, 0.0), -1)
        Imgproc.circle(src, pt4, 10, Scalar(0.0, 255.0, 255.0), -1)

        binding.originalImage.setImageBitmap(bitmapUtil.bitmapFrom(src))

        val srcQuad = MatOfPoint2f(
            pt1,
            pt2,
            pt3,
            pt4
        )
        val dstQuad = MatOfPoint2f(
            Point(0.0, 0.0),
            Point(width - 1, 0.0),
            Point(0.0, height - 1),
            Point(width - 1, height - 1),
        )


        val perspectiveTransform = Imgproc.getPerspectiveTransform(srcQuad, dstQuad)

        var dst = Mat()
        Imgproc.warpPerspective(src, dst, perspectiveTransform, Size(0.0, 0.0))
        dst = dst.submat(0, height.toInt(), 0, width.toInt())
        binding.perspectiveImage.setImageBitmap(
            bitmapUtil.bitmapFrom(dst)
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}