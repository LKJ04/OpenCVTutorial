package com.charlezz.opencvtutorial

import com.charlezz.opencvtutorial.features.basic.BasicMenus
import com.charlezz.opencvtutorial.features.experiment.ExperimentMenus
import com.charlezz.opencvtutorial.features.filtering.FilteringMenus
import com.charlezz.opencvtutorial.features.geometry.GeometryMenus

sealed class MainMenus {

    object Basic : Menu(
        title = "기본적인 영상 처리 기법",
        MenuDirections.from(
            MenuFragmentDirections.actionMenuFragmentSelf(
                BasicMenus::class.nestedClasses
                    .sortedBy { menuKclass -> (menuKclass.objectInstance as Menu).order }
                    .map { menuKclass -> menuKclass.objectInstance as Menu }
                    .toTypedArray()
            )
        ),
        0
    )

    object Filtering : Menu(
        "필터링",
        MenuDirections.from(
            MenuFragmentDirections.actionMenuFragmentSelf(
                FilteringMenus::class.nestedClasses
                    .sortedBy { menuKclass -> (menuKclass.objectInstance as Menu).order }
                    .map { menuKclass -> menuKclass.objectInstance as Menu }
                    .toTypedArray()
            )
        ),
        1
    )

    object GeometryTransformation:Menu(
        "기하학적 변환",
        MenuDirections.from(
            MenuFragmentDirections.actionMenuFragmentSelf(
                GeometryMenus::class.nestedClasses
                    .sortedBy { menuKclass -> (menuKclass.objectInstance as Menu).order }
                    .map { menuKclass -> menuKclass.objectInstance as Menu }
                    .toTypedArray()
            )
        ),
        2
    )

    object Experiment:Menu(
        "실험실",
        MenuDirections.from(
            MenuFragmentDirections.actionMenuFragmentSelf(
                ExperimentMenus::class.nestedClasses
                    .sortedBy { menuKclass -> (menuKclass.objectInstance as Menu).order }
                    .map { menuKclass -> menuKclass.objectInstance as Menu }
                    .toTypedArray()
            )
        )
    )
}