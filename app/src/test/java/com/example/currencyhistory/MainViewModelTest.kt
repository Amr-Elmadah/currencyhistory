package com.example.currencyhistory

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import com.example.currencyhistory.dataSource.RepositorySource
import com.example.currencyhistory.ui.MainViewModel
import com.example.currencyhistory.util.formatDate
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*
import javax.inject.Inject


@RunWith(JUnit4::class)
class MainViewModelTest {

	@Mock
	@Inject
	lateinit var mRepository: RepositorySource
	private lateinit var mainViewModel: MainViewModel
	@get:Rule
	var instantTaskExecutorRule = InstantTaskExecutorRule()

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		this.mainViewModel = MainViewModel(this.mRepository)
	}

	@Test
	fun checkResponseListNotNullOrEmpty() {
		val testOwner = TestLifecycleOwner()
		testOwner.handleEvent(Lifecycle.Event.ON_CREATE)
		mainViewModel.notifyUpdate.observe(testOwner, androidx.lifecycle.Observer {
			Assert.assertTrue(!mainViewModel.currencyList.isNullOrEmpty())
		})
		val today = Calendar.getInstance()
		for (i in 0 until 8) {
			mainViewModel.getRateItem(formatDate(today.time))
			today.add(Calendar.DAY_OF_WEEK, -1)
		}
	}
}
