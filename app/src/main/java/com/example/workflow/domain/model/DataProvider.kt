package com.example.workflow.domain.model

class DataProvider {
    companion object {
        private val taskList1 = arrayListOf<TaskModel>(
            TaskModel(
                "Navigation 1",
                "Navigation menu to info",
                true, false
            ),
            TaskModel(
                "Desing About",
                "desing button About int menu",
                false, false
            ),
            TaskModel(
                "Desing 'Add workflow'",
                "Desing button 'Add workflow' in menu",
                false, false
            )
        )
        private val taskList2 = arrayListOf<TaskModel>(
            TaskModel(
                "set data",
                "Add the data into the view menu",
                true, false
            )
        )
        private val taskList3 = arrayListOf<TaskModel>(
            TaskModel(
                "Desing view V.1",
                "Desing menu and info screens V.1",
                false, true
            )
        )



        private val stateList = arrayListOf<StateModel>(
            StateModel("New", taskList1),
            StateModel("In progress", taskList2),
            StateModel("Complited", taskList3)
        )
        val list = listOf<WorkflowModel>(
            WorkflowModel(0, "Trabajo", stateList),
            WorkflowModel(1, "Casa", stateList),
            WorkflowModel(2, "Universidad", stateList),
            WorkflowModel(3, "Varios", stateList)

        )
    }
}