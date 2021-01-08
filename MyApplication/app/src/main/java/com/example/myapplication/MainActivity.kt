package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        getUserinfo("yoo")
        postTest("yoo")

    }

    val dbnAPIserve by lazy {
        dbnAPI.create()
    }

    var disposable: Disposable? = null

    private fun getUserinfo(username: String) {

        disposable =
            dbnAPIserve.getuserinfo(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> login(result) },
                    { error -> Log.d("error:", error.message.toString()) }
                )
    }

    private fun login(result: dbnModels.Result) {

        Log.d("result code", result.result.toString())

        if (result.result == 1){

            val userinfo = result.userinfo
            Log.d("result animaltestflag", userinfo[0].animaltestflag.toString())
            Log.d("result countarray", userinfo[0].countarray)
            Log.d("result userpw", userinfo[0].userpw)
        }

    }

    private fun postTest(param: String){
        disposable =
            dbnAPIserve.postTest(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> pp(result) },
                    { error -> Log.d("error:", error.message.toString()) }
                )
    }

    private fun pp(result: String){
        Log.d("pp result", result)
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

}