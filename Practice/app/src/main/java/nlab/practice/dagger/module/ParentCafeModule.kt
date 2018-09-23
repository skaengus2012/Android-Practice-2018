package nlab.practice.dagger.module

import dagger.Module
import dagger.Provides
import nlab.practice.dagger.component.CoffeeComponent
import nlab.practice.dagger.model.CafeInfo
import javax.inject.Singleton

/**
 * SubComponent 를 가지는 컴포넌트 정의
 *
 * @author Doohyun
 */
@Module(subcomponents = [CoffeeComponent::class])
class ParentCafeModule {

    @Singleton
    @Provides
    fun provideCafeInfo() : CafeInfo = CafeInfo()
}