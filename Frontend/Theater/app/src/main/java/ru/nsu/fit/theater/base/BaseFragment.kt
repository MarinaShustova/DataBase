package ru.nsu.fit.theater.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.slf4j.LoggerFactory

abstract class BaseFragment: Fragment() {

    abstract val layoutId: Int

    val logger = LoggerFactory.getLogger(javaClass.simpleName) ?: throw Exception("logger is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.debug("onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (view == null) {
            inflater.inflate(layoutId, container, false)
        } else {
            view
        }
    }

    override fun onStart() {
        super.onStart()
        logger.debug("onStart")
    }

    override fun onResume() {
        super.onResume()
        logger.debug("onResume")
    }

    override fun onPause() {
        super.onPause()
        logger.debug("onPause")
    }

    override fun onStop() {
        super.onStop()
        logger.debug("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.debug("onDestroy")
    }


}