# FashionSelection
FashionSelection by weather

#개요
- XmlParser를 이용하여 기상청 RSS 데이터 받아온 후 
- 사용자에게 기온에 알맞는 옷 추천 기능 구현 

#사용 언어 및 데이터 출처  
- Kotlin 사용 
- 기상청 RSS 사용 


#Contract 
1. MainContract : MainActivity와 MainPresenter에 대한 Interface 작성 
2. WeatherListContract : WeatherListActivity와 WeatherListPresenter에 대한 Interface 작성

#Model
1. GetWeaterData : Presenter에게 데이터 요청이 들어오면, XML 데이터를 파싱한 후 리스트 형태로 응답 

#Presenter
1. MainPresenter : 
- View인 MainActivity에게 요청이 들어오면 Model에게 데이터 요청 
- Model에게 받은 데이터를 뷰인 MainActivity로 전달 
2. WeatherListPresenter : 
- View인 WeatherListActivity에게 요청이 들어오면 Model에게 데이터 요청 
- Model에게 받은 데이터를 뷰인 WeatherListActivity로 전달

#View
1. MainActivity  
- MainPresenter에게 데이터 요청 
- MainPresenter에게 응답 데이터를 받은 후, UI 변경(날씨,기온,습도,적합한 옷)   
2. WeatherListActivity 
- WeatherListPresenter에게 데이터 요청 
- WeatherListPresenter에게 응답 데이터를 받은 후, 어뎁터 갱신(리스트 뷰, 시간 별 온도,습도,날씨)

