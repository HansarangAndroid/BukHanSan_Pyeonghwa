package com.example.lecturesopt28th.home.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragment
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.lecturesopt28th.LiveDataUtil.getOrAwaitValue
import com.example.lecturesopt28th.MainActivity
import com.example.lecturesopt28th.home.api.SearchUserApiService
import com.example.lecturesopt28th.home.data.dto.ResponseFollowers
import com.example.lecturesopt28th.home.data.entity.FollowerModel
import com.example.lecturesopt28th.home.data.entity.UserModel
import com.example.lecturesopt28th.home.data.repository.SearchUserRepository
import com.example.lecturesopt28th.home.viewmodel.HomeViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Rule
    @JvmField
    var instantRule = InstantTaskExecutorRule()

    @Inject
    lateinit var searchUserApiService: SearchUserApiService

    @Inject
    lateinit var searchUserRepository: SearchUserRepository

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = HomeViewModel(searchUserRepository)
    }

    //별로 무의미한 테스트...?
    @Test
    fun `GithubApi로부터_User_Response를_받아오는데_에러가_없는지_테스트`() {
        searchUserApiService.getUserInfo("SONPYEONGHWA")
            .test()
            .assertNoErrors()
    }

    @Test
    fun `SearchUserRepository에서_데이터_맵핑이_제대로_되는지_테스트`() {
        val mockUser =
            UserModel("https://avatars.githubusercontent.com/u/56873136?v=4", "SON PYEONG HWA")
        searchUserRepository.getUserInfo("SONPYEONGHWA")
            .test()
            .assertValue(mockUser)
    }

    @Test
    fun `FollowerMappingTest`() {
        val fakeResponseFollower =
            ResponseFollowers("sson_peace","sson_peace","sson_peace","sson_peace","sson_peace","sson_peace","sson_peace",1,"sson_peace","sson_peace","sson_peace","sson_peace","sson_peace",false,"sson_peace","sson_peace","sson_peace","sson_peace")
        val fakeFollowerModel = FollowerModel("sson_peace", "sson_peace")
        Assert.assertEquals(fakeResponseFollower.toFollowerModel(), fakeFollowerModel)
    }
}