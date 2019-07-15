package hr.ferit.matejmijic.ada_taskie.common

import android.content.Context
import android.widget.Toast

fun Context.displayToast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

fun Context.displayToast(resourceId: Int) = Toast.makeText(this, getString(resourceId), Toast.LENGTH_SHORT).show()