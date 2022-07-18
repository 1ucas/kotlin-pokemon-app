@file:Suppress("PackageNaming")
package pokemontcg.libraries.ui_components.extensions

import android.app.Activity
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

fun Activity.createLoadingDialog(isCancelable: Boolean = false): AlertDialog {
    val progressBar = ProgressBar(this).apply {
        isIndeterminate = true
    }

    val dialog = AlertDialog.Builder(this)
        .setView(progressBar)
        .setCancelable(isCancelable)
        .create()

    dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    dialog.window!!.setLayout(LoadingDialogDimensions.width, LoadingDialogDimensions.height)

    return dialog
}

fun Fragment.createLoadingDialog(isCancelable: Boolean = false): AlertDialog? {
    this.context?.apply {
        val progressBar = ProgressBar(this).apply {
            isIndeterminate = true
        }

        val dialog = AlertDialog.Builder(this)
            .setView(progressBar)
            .setCancelable(isCancelable)
            .create()

        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window!!.setLayout(LoadingDialogDimensions.width, LoadingDialogDimensions.height)

        return dialog
    }
    return null
}
