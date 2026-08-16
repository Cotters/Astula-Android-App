package com.noble.features.upload.impl

import com.noble.features.upload.api.domain.NewWardrobeItem
import com.noble.features.upload.impl.domain.SaveWardrobeItemUseCase
import com.noble.tests.TestDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UploadViewModelShould {

    @get:Rule
    private val testDispatcher = TestDispatcherRule()

    @RelaxedMockK
    private lateinit var saveWardrobeItemUseCase: SaveWardrobeItemUseCase

    val viewModel by lazy { UploadViewModel(saveWardrobeItemUseCase) }

    val viewState: UploadViewState
        get() = viewModel.viewState.value

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `start with an empty state`() {
        assert(viewState.name.isEmpty())
        assert(viewState.description.isEmpty())
        assertFalse(viewState.isSaving)
        assertThat(viewState, equalTo(UploadViewState()))
    }

    @Test
    fun `set state to saving when submit pressed`() {
        viewModel.onViewEvent(UploadViewEvent.SubmitPressed)

        assert(viewState.isSaving)
    }

    @Test
    fun `save item when submit pressed`() {
        val name = "Test Name"
        val description = "Test Description"
        viewModel.onViewEvent(UploadViewEvent.NameUpdated(name))
        viewModel.onViewEvent(UploadViewEvent.DescriptionUpdated(description))
        viewModel.onViewEvent(UploadViewEvent.SubmitPressed)

        val slot = slot<NewWardrobeItem>()
        coVerify { saveWardrobeItemUseCase.run(capture(slot)) }
        assertThat(slot.captured.name, equalTo(name))
        assertThat(slot.captured.description, equalTo(description))
    }
}