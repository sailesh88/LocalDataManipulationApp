package com.test.localdataapp.models

class UserDataSource {

    companion object {
        fun createDataSet(): ArrayList<UserData> {
            val list = ArrayList<UserData>()
            for (i in 0..15) {
                list.add(
                    UserData(
                        1000 + i,
                        1 + i,
                        "title $i",
                        getDescription(i),
                    )
                )
            }
            return list
        }

        private fun getDescription(i: Int): String {
            return "$i. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae condimentum mi. Maecenas laoreet tempus arcu vitae facilisis. Nulla facilisi. Duis iaculis ullamcorper enim ut aliquam. Suspendisse tincidunt dapibus viverra. Etiam tincidunt neque diam, sit amet aliquet dui pretium sed. Phasellus sed ultrices elit. Etiam commodo ultrices justo in euismod. Etiam eu mattis orci. Pellentesque nec congue ipsum. Maecenas finibus sed purus et lacinia."

        }
    }
}