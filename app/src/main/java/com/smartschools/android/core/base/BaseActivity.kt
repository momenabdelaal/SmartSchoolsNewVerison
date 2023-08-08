package com.smartschools.android.core.base


import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.smartschools.android.R
import com.smartschools.android.core.appUtils.Constants.LANGUAGE_ARABIC
import com.smartschools.android.core.appUtils.drawQR
import com.smartschools.android.core.appUtils.localization.LocaleHelper
import com.smartschools.android.core.appUtils.localization.LocalizationUtils
import com.smartschools.android.core.appUtils.nav.MeowBottomNavigation
import com.smartschools.android.core.appUtils.util.InternetConnectivity
import com.smartschools.android.core.appUtils.util.addBasicItemDecoration
import com.smartschools.android.data.model.side_menu.SideMenuItem
import com.smartschools.android.data.persistentStorage.sharedPref.SharedPreferencesImpl
import com.smartschools.android.databinding.ActivityBaseBinding
import com.smartschools.android.ui.home.adapter.SideMenuAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.*


@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    var fragment: Fragment? = null
    var bundle: Bundle? = null
    private var progressDialog: Dialog? = null
    var dialogDismissThread: Job? = null
    var databinding: ActivityBaseBinding? = null
    var mNavController: NavController? = null
    val viewModel: BaseViewModel by viewModels()

    var imageProfile: String? = ""
    private lateinit var connectionLiveData: InternetConnectivity

    private val adapter: SideMenuAdapter by lazy {
        SideMenuAdapter {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val configuration = resources.configuration
////        configuration.locale = locale
//        configuration.fontScale = 1.toFloat() //0.85 small size, 1 normal size, 1,15 big etc
//        val metrics = DisplayMetrics()
//        windowManager.defaultDisplay.getMetrics(metrics)
//        metrics.scaledDensity = configuration.fontScale * metrics.density
//
//
//        if (!Constants.densities.contains(metrics.density))
//            configuration.densityDpi = (LocalizationUtils.getDensity(this) * 170f).toInt()
//        resources.updateConfiguration(configuration, metrics)
////        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
//
        databinding = DataBindingUtil.setContentView(this@BaseActivity, R.layout.activity_base)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        databinding!!.lifecycleOwner = this
        mNavController = Navigation.findNavController(this, R.id.navHostFragment)
//        databinding!!.ivUser.setOnClickListener {
//            Navigation.findNavController(this@BaseActivity, R.id.navHostFragment)
//                .navigate(R.id.settingsFragment)
//            databinding!!.drawerlayout.closeDrawer(GravityCompat.START)
//        }
//        observeUIState()
//
//
//        if (SharedPreferencesImpl(this).getLanguage() == LANGUAGE_ARABIC) {
//            databinding!!.flagIcon.scaleType = ImageView.ScaleType.FIT_END
//            databinding!!.back.scaleType = ImageView.ScaleType.FIT_START
//        } else {
//            databinding!!.flagIcon.scaleType = ImageView.ScaleType.FIT_START
//            databinding!!.back.scaleType = ImageView.ScaleType.FIT_END
//        }
//        databinding!!.drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
//
//        databinding!!.ivBack.setOnClickListener { onBackPressed() }
//
//        connectionLiveData = InternetConnectivity(applicationContext)
//        connectionLiveData.observe(this) { isAvailable ->
//            when (isAvailable) {
//                true -> {
//                    val id = mNavController!!.currentDestination!!.id
//
//                    if (id == R.id.noConnectionFragment) {
//                        //mNavController?.navigate(R.id.homeNewFragment)
//                        mNavController?.popBackStack()
//                        if (mNavController?.currentDestination?.id == R.id.homeNewFragment)
//                            mNavController?.navigate(R.id.homeNewFragment)
//                    }
//                }
//
//                false -> {
//                    val bunder = bundleOf("test" to "1")
//                    mNavController?.navigate(R.id.noConnectionFragment, bunder)
//                }
//            }
//        }
//        itemsNav()
//        prepareSideMenu()
        Log.d("dsdfs", "onCreate: " + SharedPreferencesImpl(this@BaseActivity).getLanguage())

        if (SharedPreferencesImpl(this@BaseActivity).getLanguage()
                .isEmpty() || SharedPreferencesImpl(this@BaseActivity).getLanguage() == LANGUAGE_ARABIC
        ) {
            LocaleHelper.initLanguage(this@BaseActivity, "ar")
            databinding!!.root.layoutDirection = View.LAYOUT_DIRECTION_RTL
        } else {
            LocaleHelper.initLanguage(this@BaseActivity, "en")
            databinding!!.root.layoutDirection = View.LAYOUT_DIRECTION_LTR
        }

        initMeowNav()
//
    }

    private fun initMeowNav() {
        databinding!!.meowBottomNavigation.apply {
            add(
                MeowBottomNavigation.Model(
                    1, R.drawable.ic_notification_dashboard
                )
            )
            add(
                MeowBottomNavigation.Model(
                    2, R.drawable.ic_comment_dashboard
                )
            )
            add(MeowBottomNavigation.Model(3, R.drawable.ic_home))
            add(
                MeowBottomNavigation.Model(
                    4, R.drawable.ic_network_soical
                )
            )
            add(
                MeowBottomNavigation.Model(
                    5, R.drawable.ic_user_dashboard
                )
            )
        }


        databinding!!.meowBottomNavigation.setOnReselectListener {

            when (it.id) {
                1 -> {
//                        startFragment(NotificationsFragment())
                }

                2 -> {

//                        startActivity(new Intent(MainActivity.this, MessageActivity.class));
//                        startFragment(ParentMessageFragment())
                }

                3 -> {
//                        if (tables.size >= 1) switchAccounts() else defaultGroups()
                }

                4 -> {
//                        startActivity(
//                            Intent(this@MainActivity, WebViewActivity::class.java)
//                                .putExtra("link", "https://smartschools.network/social/")
//                        )
                }

                5 -> {
//                        startFragment(EditProfileFragment())
                }
            }

        }


        databinding!!.meowBottomNavigation.setCount(
            1,
            "10")


    }

    private fun observeUIState() {
//        lifecycleScope.launch {
//            viewModel.uiState.flowWithLifecycle(lifecycle).collect(::updateUI)
//
//        }


    }

//    override fun onBackPressed() {
//        when (mNavController!!.currentDestination!!.id) {
//
//            R.id.mailFragment, R.id.archiveFragment, R.id.settingsFragment, R.id.fragmentProjectDetails -> {
//                mNavController?.navigate(R.id.homeNewFragment)
//            }
//
//            else -> {
//                when (mNavController!!.currentDestination!!.id) {
//                    R.id.mailDetailsFragment -> {
//                        mNavController?.navigate(R.id.mailFragment)
//                    }
//                    R.id.fragmentContractorMail, R.id.fragmentTrackingProject, R.id.fragmentProjectFiles, R.id.showLocationFragment -> {
//                        mNavController!!.navigateUp() || super.onSupportNavigateUp()
//                    }
//                    else -> {
//                        super.onBackPressed()
//                    }
//
//                }
////
////                if (mNavController!!.currentDestination!!.id == R.id.mailDetailsFragment)
////
////                else if (mNavController!!.currentDestination!!.id == R.id.fragmentContractorMail||)
////                Log.d("there", "onBackPressed: "+mNavController!!.currentDestination!!.id)
////
//            }
//        }
//    }
//    override fun onSupportNavigateUp(): Boolean {
//        return mNavController!!.navigateUp() || super.onSupportNavigateUp()
//    }

//    @SuppressLint("NotifyDataSetChanged")
//    private fun updateUI(result: BaseViewModel.UiState) {
//        when (result) {
//            is BaseViewModel.UiState.Error -> {
////                toast(result.errorMsg)
//                Log.d("resultER", "updateUI: " + result.errorMsg)
//                viewModel.navToIdle()
//            }
//
//            is BaseViewModel.UiState.Loading -> {
//            }
//
//            is BaseViewModel.UiState.Success -> {
//                if (result.data.Success) {
//                    val save = SharedPreferencesImpl(this)
//                    save.setNotificationCount(result.data.data.toString())
//                    navAdapter?.list?.get(1)?.itemNotifications = result.data.data.toString()
//                    navAdapter?.notifyDataSetChanged()
//                }
//                viewModel.navToIdle()
////                else
////                    BaseViewModel.UiState.Error(checkResponseCode(result.data.Code))
//            }
//            is BaseViewModel.UiState.SuccessChangePasswordStatus -> {
//                Log.d("isPass", "updateUI: " + result.data.data)
//                viewModel.navToIdle()
////                else
////                    BaseViewModel.UiState.Error(checkResponseCode(result.data.Code))
//            }
//
//            is BaseViewModel.UiState.Idle -> {
//
//            }
//        }
//    }
//
//    private fun updateUIProfile(result: SettingsViewModel.UiState) {
//        when (result) {
//            is SettingsViewModel.UiState.Error -> {
////                toast(result.errorMsg)
//                Log.d("resultPro", "updateUI: " + result.errorMsg)
//
//            }
//
//            is SettingsViewModel.UiState.Loading -> {
//            }
//
//
//            is SettingsViewModel.UiState.Success -> {
//                if (result.data.Success) {
//
//                    databinding!!.name.text = result.data.data.User_Name
//
//                    Glide.with(this@BaseActivity).load(result.data.data.User_Image)
//                        .dontAnimate().error(R.drawable.ic_profile)
//                        .into(databinding!!.ivProfile)
//
//                    Glide.with(this@BaseActivity).load(result.data.data.User_Image)
//                        .dontAnimate().error(R.drawable.ic_profile)
//                        .into(databinding!!.ivUser)
//
//
//
//                    Log.d("TAG", "updateUIProfile: " + result.data.data.User_Image)
//                    viewModelSettings.navigateFragment()
//                }
//
////                else
////                    SettingsViewModel.UiState.Error(checkResponseCode(result.data.Code))
//            }
//
//            is SettingsViewModel.UiState.SuccessChangePassword -> {
//
//            }
//
//            is SettingsViewModel.UiState.SuccessUpdateImage -> {
//
//            }
//
//            is SettingsViewModel.UiState.Idle -> {
//                viewModelSettings.navigateFragment()
//
//            }
//
//        }
//    }


    @SuppressLint("ResourceType")
    private fun prepareSideMenu() {
        databinding!!.ivBarcode.setImageResource(R.drawable.holder)
        databinding!!.ivMainToolbarMenu.setOnClickListener {

            if (databinding!!.drawerlayout.isDrawerOpen(GravityCompat.START)) {
                databinding!!.drawerlayout.closeDrawer(GravityCompat.START)
            } else {
                databinding!!.drawerlayout.openDrawer(GravityCompat.START)
            }
        }

        databinding!!.ivBack.setOnClickListener {
            onBackPressed()
        }

        val toggle = ActionBarDrawerToggle(
            this, databinding!!.drawerlayout, 1, 0
        )

        toggle.isDrawerIndicatorEnabled = false

        databinding!!.drawerlayout.addDrawerListener(toggle)

        toggle.syncState()

        toggle.toolbarNavigationClickListener = View.OnClickListener {


            if (databinding!!.drawerlayout.isDrawerOpen(GravityCompat.START)) {
                databinding!!.drawerlayout.closeDrawer(GravityCompat.START)
            } else {
                databinding!!.drawerlayout.openDrawer(GravityCompat.START)
            }
        }
    }

    //
    private fun itemsNav() {
        val item = SideMenuItem()
        val navItemList: MutableList<SideMenuItem> = ArrayList()
        navItemList.clear()
        navItemList.addAll(item.initListStudents(this@BaseActivity))
        adapter.reset()
        adapter.submitFullList(navItemList)
        databinding!!.recyclerView.adapter = adapter
        databinding!!.recyclerView.addBasicItemDecoration(R.dimen.item_decoration_extra_large_margin)
        drawQR(databinding!!.ivBarcode, "1101000")

    }


//
//    var navAdapter: NavAdapter? = null
//
//    private fun setRecyclerView(data: MutableList<ModelItem>, s: String) {
//        val linearLayoutManager = LinearLayoutManager(this)
//        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//        databinding!!.recyclerView.layoutManager = linearLayoutManager
//        databinding!!.recyclerView.itemAnimator = DefaultItemAnimator()
//        navAdapter = NavAdapter()
//        databinding!!.recyclerView.adapter = navAdapter
//
//
//        navAdapter!!.addList(data)
//
//        navAdapter!!.onItemClickListener = object : OnItemClick {
//            override fun onItemClick(view: View, position: Int) {
//                when (data[position].itemId) {
//                    1 -> {
//                        databinding!!.tvMainEmployeeName.text = getString(R.string.home)
//                        Navigation.findNavController(this@BaseActivity, R.id.navHostFragment)
//                            .navigate(R.id.homeNewFragment)
//                        Constants.isHomeStatus = true
//                        databinding!!.drawerlayout.closeDrawer(GravityCompat.START)
//                    }
//
//                    2 -> {
//                        databinding!!.tvMainEmployeeName.text = getString(R.string.inbox)
//                        Navigation.findNavController(this@BaseActivity, R.id.navHostFragment)
//                            .navigate(R.id.mailFragment)
//                        databinding!!.drawerlayout.closeDrawer(GravityCompat.START)
//                    }
//
//                    3 -> {
//                        databinding!!.tvMainEmployeeName.text =
//                            getString(R.string.project_archive)
//                        Navigation.findNavController(this@BaseActivity, R.id.navHostFragment)
//                            .navigate(R.id.archiveFragment)
//                        databinding!!.drawerlayout.closeDrawer(GravityCompat.START)
//                    }
//
//                    4 -> {
//                        databinding!!.tvMainEmployeeName.text =
//                            getString(R.string.profile_setting)
//                        Navigation.findNavController(this@BaseActivity, R.id.navHostFragment)
//                            .navigate(R.id.settingsFragment)
//                        databinding!!.drawerlayout.closeDrawer(GravityCompat.START)
//                    }
//
//                    5 -> {
//                        exitDialog(8, true)
//                    }
//                }
//            }
//        }
//
//    }
//
//    private fun exitDialog(layoutId: Int, isLogut: Boolean) {
//        val dialog = Dialog(this)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable(false)
//        dialog.setContentView(R.layout.custom_exit_dialog)
//
//        val btnSubmit = dialog.findViewById<View>(R.id.tvSubmit) as TextView
//        val btnCancel = dialog.findViewById<View>(R.id.tvCancel) as TextView
//        if (layoutId == 8) {
//            btnSubmit.setOnClickListener {
//                val lang = SharedPreferencesImpl(this).getLanguage()
//
//                if (isLogut) {
//                    databinding!!.drawerlayout.closeDrawer(GravityCompat.START)
//                    dialog.dismiss()
//                    SharedPreferencesImpl(this@BaseActivity).clearAll()
//                    SharedPreferencesImpl(this).setLanguage(lang)
//
//                    Navigation.findNavController(this@BaseActivity, R.id.navHostFragment)
//                        .navigate(R.id.loginFragment)
//                    sharedViewModel.remoteFilters.value.apply { AppliedFilterData() }
////                    sharedViewModel.updateRemoteFilters(AppliedFilterData())
//                    if (!sharedViewModel.isHomeMapFragmentActive)
//                        sharedViewModel.isHomeMapFragmentActive = true
//                } else {
//                    databinding!!.drawerlayout.closeDrawer(GravityCompat.START)
//                    dialog.dismiss()
//                    SharedPreferencesImpl(this@BaseActivity).clearAll()
//                    SharedPreferencesImpl(this).setLanguage(lang)
//
//                    Navigation.findNavController(this@BaseActivity, R.id.navHostFragment)
//                        .navigate(R.id.loginFragment)
//                    sharedViewModel.remoteFilters.value.apply { AppliedFilterData() }
////                    sharedViewModel.updateRemoteFilters(AppliedFilterData())
//                    if (!sharedViewModel.isHomeMapFragmentActive)
//                        sharedViewModel.isHomeMapFragmentActive = true
//
//                    Navigation.findNavController(this@BaseActivity, R.id.navHostFragment)
//                        .navigate(R.id.loginFragment)
//                    if (!sharedViewModel.isHomeMapFragmentActive)
//                        sharedViewModel.isHomeMapFragmentActive = true
//                }
//
//            }
//        } else {
//            btnSubmit.setOnClickListener {
//                dialog.dismiss()
//                finishAffinity()
//                exitProcess(0)
//            }
//        }
//
//        btnCancel.setOnClickListener { dialog.dismiss() }
//
//        dialog.show()
//        val metrics: DisplayMetrics = resources.displayMetrics
//        val width: Int = metrics.widthPixels
//        dialog.window?.setLayout(
//            width - 58, ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//    }

//    @DelicateCoroutinesApi
//    fun showProgress(show: Boolean) {
//        if (progressDialog == null) {
//            progressDialog = Dialog(this)
//            progressDialog?.window?.setBackgroundDrawable(ColorDrawable(0))
//            progressDialog?.setContentView(R.layout.layout_loading_screen)
//        }
//        when {
//            show -> {
//                if (!isFinishing && !progressDialog!!.isShowing) {
//                    // to disable back button while dialog is showing
//                    progressDialog?.setCancelable(false)
//                    progressDialog?.setCanceledOnTouchOutside(false)
//                    progressDialog?.show()
//                    dialogDismissThread = GlobalScope.launch {
//                        delay(10000)
//                        dismissProgressDialog()
//                    }
//                }
//            }
//
//            else -> try {
//                dismissProgressDialog()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
//
//    fun dismissProgressDialog() {
//        if (progressDialog?.isShowing!! && !isFinishing) {
//            dialogDismissThread?.cancel()
//            progressDialog?.dismiss()
//        }
//    }


    private val listener =
        NavController.OnDestinationChangedListener { controller, destination, _ ->


            if (destination.label.toString()
                    .contains("SplashFragment") || destination.label.toString()
                    .contains("fragment_login") || destination.label.toString()
                    .contains("fragment_welcome") || destination.label.toString()
                    .contains("fragment_language") || destination.label.toString()
                    .contains("ConfirmForgetPasswordFragment") || destination.label.toString()
                    .contains("changePasswordAfterLogin") || destination.label.toString()
                    .contains("ChangePassword")
            ) {
                databinding!!.ivBack.visibility = View.GONE
//                databinding!!.ivUser.visibility = View.GONE
                databinding!!.sideMenu.visibility = View.GONE
                databinding!!.clMainToolbarContainer.visibility = View.GONE
                databinding!!.meowBottomNavigation.visibility = View.GONE


            } else if (destination.label.toString().contains("fragment_home")) {

                databinding!!.drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                databinding!!.ivBack.visibility = View.GONE
                databinding!!.ivMainToolbarMenu.visibility = View.VISIBLE
//                databinding!!.ivUser.visibility = View.VISIBLE
                databinding!!.titleBar.text = getString(R.string.dashboard)
                databinding!!.sideMenu.visibility = View.VISIBLE
                databinding!!.clMainToolbarContainer.visibility = View.VISIBLE
                databinding!!.meowBottomNavigation.visibility = View.VISIBLE
//
//                if (SharedPreferencesImpl(this@BaseActivity).getApiKeyToken().isNotEmpty()) {
//                    viewModel.getMessageCount()
//                    viewModelSettings.userGetInfo()
//                }
//

                databinding!!.meowBottomNavigation.show(3, true)
                itemsNav()
                prepareSideMenu()


            } else if (destination.label.toString()
                    .contains("NoConnectionFragment") || destination.label.toString()
                    .contains("ServerErrorFragment")
            ) {
                databinding!!.clMainToolbarContainer.visibility = View.GONE

            } else if (destination.label.toString().contains("settingsFragment")) {

//
                databinding!!.ivBack.visibility = View.VISIBLE
//                databinding!!.ivUser.visibility = View.GONE
                databinding!!.sideMenu.visibility = View.VISIBLE
//                databinding!!.tvMainEmployeeName.text = getString(R.string.profile_setting)
                databinding!!.ivMainToolbarMenu.visibility = View.VISIBLE
                databinding!!.clMainToolbarContainer.visibility = View.VISIBLE
                databinding!!.drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
//                if (SharedPreferencesImpl(this@BaseActivity).getApiKeyToken().isNotEmpty()) {
//                    viewModel.getMessageCount()
//
//                }
//
                itemsNav()
                prepareSideMenu()
            } else {
                databinding!!.ivBack.visibility = View.VISIBLE
//                databinding!!.ivUser.visibility = View.GONE
                databinding!!.ivMainToolbarMenu.visibility = View.GONE
                databinding!!.titleBar.text = getString(R.string.dashboard)
                databinding!!.sideMenu.visibility = View.VISIBLE
                databinding!!.clMainToolbarContainer.visibility = View.VISIBLE
                databinding!!.drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                itemsNav()
                prepareSideMenu()
//                if (SharedPreferencesImpl(this@BaseActivity).getApiKeyToken().isNotEmpty()) {
//                    viewModelSettings.loadingFragment()
//                    viewModelSettings.userGetInfo()
//
//                }

//                setTitleScreen(destination.label.toString())
                Log.d("nav", "on NAv: " + destination.toString())

            }
        }

    override fun onStart() {
        super.onStart()
        mNavController?.addOnDestinationChangedListener(listener)
        LocalizationUtils.setDefaultFontConfiguration(this)
    }

    override fun onResume() {
        super.onResume()
        mNavController?.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        mNavController?.removeOnDestinationChangedListener(listener)
        super.onPause()

    }

//
//    fun checkResponseCode(
//        code: Int
//    ): String {
//        val value = when (code) {
//            204 -> getString(R.string.use_password_incorrect)
//            404 -> getString(R.string.server_error)
//            401 -> getString(R.string.token_expite)
//            403 -> getString(R.string.code_correct)
//            400 -> getString(R.string.failed_remove)
//            else -> getString(R.string.unknown_error)
//        }
//        return value
//    }

//    private fun setTitleScreen(labels: String) {
//        if (labels.contains("settingsFragment")) {
//            databinding!!.tvMainEmployeeName.text =
//                getString(R.string.profile_setting)
//
//
//        } else if (labels.contains("fragment_mail")) {
//            databinding!!.tvMainEmployeeName.text =
//                getString(R.string.mail_inbox)
//            databinding!!.ivMainToolbarMenu.visibility = View.VISIBLE
//        } else if (labels.contains("mailDetailsFragment")) {
//            databinding!!.tvMainEmployeeName.text =
//                getString(R.string.mail_details)
//            databinding!!.ivMainToolbarMenu.visibility = View.GONE
//        } else if (labels.contains("fragment_archive")) {
//            databinding!!.tvMainEmployeeName.text =
//                getString(R.string.project_archive)
//            databinding!!.ivMainToolbarMenu.visibility = View.VISIBLE
//
//        } else if (labels.contains("fragmentProjectDetails")) {
//            databinding!!.tvMainEmployeeName.text =
//                getString(R.string.project_details)
//            databinding!!.ivMainToolbarMenu.visibility = View.GONE
//
//        } else if (labels.contains("fragmentProjectFiles")) {
//            databinding!!.tvMainEmployeeName.text =
//                getString(R.string.files_project)
//            databinding!!.ivMainToolbarMenu.visibility = View.GONE
//        } else if (labels.contains("fragmentContractorMail")) {
//            databinding!!.tvMainEmployeeName.text =
//                getString(R.string.chat_with_const)
//            databinding!!.ivMainToolbarMenu.visibility = View.GONE
//
//        } else if (labels.contains("fragmentTrackingProject")) {
//            databinding!!.ivMainToolbarMenu.visibility = View.GONE
//            databinding!!.tvMainEmployeeName.text =
//                getString(R.string.tracking_project)
//        }else if (labels.contains("OperationProjectFragment")) {
//            databinding!!.ivMainToolbarMenu.visibility = View.GONE
//            databinding!!.tvMainEmployeeName.text =
//                getString(R.string.operation_projects)
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("test", "onDestroy: "+SharedPreferencesImpl(this).getRememberMe())
//        if (SharedPreferencesImpl(this).getRememberMe() == "false")
//            SharedPreferencesImpl(this).clearAll()
//    }
}