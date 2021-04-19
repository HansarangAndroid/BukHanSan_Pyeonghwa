## First Week Assignment📚
<p align="center">
<img src = "ezgif com-gif-maker (4)](https://user-images.githubusercontent.com/56873136/115293806-b9d56800-a192-11eb-9808-603d956661ce.gif" width = 26%> <img src = "https://user-images.githubusercontent.com/56873136/115291922-49c5e280-a190-11eb-8e50-e848f2da21f9.gif" width = 26%> <img src = "https://user-images.githubusercontent.com/56873136/115292093-8265bc00-a190-11eb-97d7-28557112c2a1.gif" width = 26%></p>


### 🔥1주차 과제를 수행하면서 느낀점🔥  
조금 더 심화된 학습을 위해 LiveData, Dagger Hilt, RxJava, Navigation을 이용하여 과제를 수행해보았습니다.  
과제를 하면서 애매모호했던 지식도 검색을 해보면서 다시 학습할 수 있는 기회였습니다.  
JetPack Navigation을 이용하여 UI변경 및 데이터 전달을 구현해보면서 Fragment 사용에 익숙해질 수 있었습니다:)  
HomeFragment의 프로필은 Github user api를 이용하여 ID를 입력하면 가져올 수 있도록 하였습니다.  
부족한 부분은 추후 과제를 수행하면서 리팩토링하겠습니다!  
  
### 💡 navigation_graph.xml  
 nav_graph를 생성한 뒤 login, signup, home fragment를 추가해주었습니다.  
이후 ui path에 따라 fragment 전환 action을 생성해주었습니다.  
  
### 📦 Argument  
설정한 Action에 따라 데이터를 전달 받기 위해 fragment에서 argument를 설정해주었습니다.  
LogInFragment에서 회원가입이 완료되면 id를 HomeFragment에 전달해야합니다.  
위 로직을 수행하기 위해 HomeFragment에서 String type의 argument 를 생성해주었습니다.  

<img src ="https://user-images.githubusercontent.com/56873136/114046600-f0260400-98c3-11eb-8304-2545240fe90b.JPG">
  
  
### 💼 FragmentContainerView 추가 및 navigation graph 설정  
MainActivity에는 fragment들을 담을 NavHostFragment를 만들어주었으며 앞서 만들었던 navigation graph를 설정해주었습니다.
```
<androidx.fragment.app.FragmentContainerView  
  android:id="@+id/host_nav_fragment"  
  android:layout_width="match_parent"  
  android:layout_height="match_parent"  
  android:name="androidx.navigation.fragment.NavHostFragment"  
  app:defaultNavHost="true"  
  app:navGraph="@navigation/navigation_graph"  
  app:layout_constraintTop_toTopOf="parent"  
  app:layout_constraintEnd_toEndOf="parent"  
  app:layout_constraintStart_toStartOf="parent"  
  app:layout_constraintBottom_toBottomOf="parent"/>
  ```  
  
    
### 👋 SignInFragment.kt  
Activity의 결과값을 받아오는 startActivityForResult처럼 Navigation을 이용하여 Fragment에서 이전 스택에서 데이터를 받아올 수 있습니다.  
navigation을 이용하면 previousBackStackEntry를 통해서 이전 스택에 있는 fragment에 접근을 할 수 있습니다.  
savedStateHandle 속성을 통해 <key, value>형태로 데이터를 전달할 수 있습니다.
  
```
private fun completeSingUp() {  
    binding.buttonSignUp.setOnClickListener {  
  if (checkInputBlank()) {  
            Toast.makeText(requireContext(), "You must input all information", Toast.LENGTH_SHORT).show()  
        } else {  
            val id = binding.edittextId.text.toString()  
            val password = binding.edittextPassword.text.toString()  
            findNavController().previousBackStackEntry?.savedStateHandle?.set("id", id)  
            findNavController().previousBackStackEntry?.savedStateHandle?.set("password", password)  
            Navigation.findNavController(binding.root).popBackStack()  
        }  
    }  
}
```  
  
### 🔓 LogInFragment.kt  
currentBackStackEntry를 통해서 직전 Back Stack에 접근할 수 있습니다.  
savedStateHandle에 저장하였던 데이터를 키값을 참조하여 가져왔고 viewModel의 LiveData의 id와 password 값을 바꿔주었습니다.  

```
private fun getSafeArgs() {  
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("id")?.observe(viewLifecycleOwner) {  
	     viewModel.id.value = it  
    }  
	findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("password")?.observe(viewLifecycleOwner){  
         viewModel.password.value = it  
    }
}
 ```


LogInFragment의 Id, Password EditText가 비워져 있지 않다면 로그인이 실행되고 HomeFragment로 이동합니다.  
  
```
private fun login() {  
    binding.buttonLogin.setOnClickListener {  
  if(checkInputText()) {  
            Toast.makeText(requireContext(), "Please input email or password", Toast.LENGTH_SHORT).show()  
        } else {  
            val action = LogInFragmentDirections.actionLogInFragmentToHomeFragment(viewModel.id.value!!)  
            Navigation.findNavController(binding.root).navigate(action)  
        }  
    }  
}  
  
private fun checkInputText(): Boolean {  
    return (binding.edittextId.text.isNullOrEmpty()  
            || binding.edittextPassword.text.isNullOrEmpty())  
}
```

기본적으로 DataBinding의 효율성을 높이기 위해 BindingAdapter를 이용하여 EditText값을 가져오거나 ImageView의 url을 넣어주었습니다.  
  
### 🏠 HomeFragment  
GitHub User api를 이용하여 github의 Profile을 가져왔습니다.  
Image Library는 Glide대신 Coil을 이용해보았습니다.  
Coil은 Coroutine base의 경량화된 이미지 로딩 라이브러리입니다.  
Glide에 비해 훨씬 가볍고 편해 최근 많이 사용하고 있는 라이브러리입니다.

```
private fun initShowUser() {  
    viewModel.userId.value = args.id  
    viewModel.getUserAccessed()  
    binding.edittextIdGithub.clearFocus()  
}
```  
  
### 📃 Dummy Text LoremIpsum  
프로필의 본인소개 스크롤뷰를 테스트하기 위해 LoremIpsum 라이브러리로 랜덤 단어를 가져왔습니다.  
Lorem은 UI테스트를 위해 이미지, 텍스트 등 여러 타입의 더미데이터를 제공합니다.  
user정보가 업데이트 될 때마다 decription.value를 설정해주었습니다.  

```
val description = MutableLiveData<String>()
description.value = LoremIpsum.getInstance().getWords(1000)
```  
  
### 🔁 생명주기에 따른 로그 출력  
<img src = "https://user-images.githubusercontent.com/56873136/114056729-ece34600-98cc-11eb-82bb-fb7bfebefd33.JPG">

