package angga.com.movielist.common

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


abstract class EndlessScrollListener(
    private val mLinearLayoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    companion object {
        const val VISIBLE_THRESHOLD_COUNT = 20
        var TAG = EndlessScrollListener::class.java
            .simpleName
    }

    private var previousTotal = 0
    private var loading = true
    private var visibleThreshold = VISIBLE_THRESHOLD_COUNT
    private var firstVisibleItem: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0

    private var currentPage = 1
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            recyclerView?.let {
                visibleItemCount = it.childCount
            }
            totalItemCount = mLinearLayoutManager.itemCount
            firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()

            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }
            if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
                // End has been reached

                // Do something
                currentPage++

                onLoadMore(currentPage)

                loading = true
            }
        }
    }

    fun setVisibleThreshold(threshold: Int) {
        visibleThreshold = threshold
    }

    abstract fun onLoadMore(page: Int)

    fun reset() {
        currentPage = 1
        previousTotal = 0
    }
}