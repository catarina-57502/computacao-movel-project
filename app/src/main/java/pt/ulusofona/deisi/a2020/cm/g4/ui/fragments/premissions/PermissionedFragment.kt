package pt.ulusofona.deisi.a2020.cm.g4.ui.fragments.premissions

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.checkSelfPermission
import pt.ulusofona.deisi.a2020.cm.g4.R


abstract class PermissionedFragment(private val requestCode: Int) : Fragment() {

    fun onRequestPermissions(context: Context, permissions: Array<String>){
        var permissionGiven = 0
        permissions.forEach {
            if(checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(permissions, requestCode)
            }else{
                permissionGiven++
            }
        }
        if(permissionGiven==permissions.size){
            onRequestPermissionsSucess()
        }
    }

    abstract fun onRequestPermissionsSucess()

    abstract fun onRequestPermissionsFailure()

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if(this.requestCode==requestCode){
            if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                onRequestPermissionsSucess()
            }else{
                onRequestPermissionsFailure()
            }
        }
    }

}