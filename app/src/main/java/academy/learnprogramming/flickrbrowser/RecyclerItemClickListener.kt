package academy.learnprogramming.flickrbrowser

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(context: Context, recyclerView: RecyclerView, private val listener: OnRecyclerClickListener)
    : RecyclerView.SimpleOnItemTouchListener() {
    private val TAG = "RecyclerOnItemClickList"

    interface OnRecyclerClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    // add the gestureDetector
    private val gestureDetector = GestureDetectorCompat(context, object: GestureDetector.SimpleOnGestureListener() {

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Log.d(TAG, "onSingleTapUp: starts")
            val childView = recyclerView.findChildViewUnder(e.x, e.y)
            if (childView != null) {  // Added 2018-12-18
                Log.d(TAG, ".onSingleTapUp calling listener.onItemClick")
                listener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView))
                return true
            } else {
                return false
            }
        }

        override fun onLongPress(e: MotionEvent) {
            Log.d(TAG, "onLongPress: starts")
            val childView = recyclerView.findChildViewUnder(e.x, e.y)
            if (childView != null) {  // Added 2018-12-18
                Log.d(TAG, ".onInterceptTouchEvent calling listener.onItemLongClick")
                listener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView))
            }
        }
    })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Log.d(TAG, ".onInterceptTouchEvent: stars $e")
        val result = gestureDetector.onTouchEvent(e)
//        return super.onInterceptTouchEvent(rv, e)
        return result
    }
}
