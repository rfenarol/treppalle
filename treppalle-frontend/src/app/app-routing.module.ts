// import {NgModule} from "@angular/core";
// import {RouterModule, Routes} from "@angular/router";
// import {AlgorithmEvaluationDetailsComponent} from "./algorithm_evaluation/algorithm-evaluation-details.component";
// import {JobDetailComponent} from "./job/job-detail.component";
// import {JobTrackerComponent} from "./job/job-tracker.component";
// import {ModelCreateComponent} from "./models/model-create.component";
// import {ModelDetailComponent} from "./models/model-detail.component";
// import {ModelsComponent} from "./models/models.component";
// import {TrainColumnsStatsComponent} from "./trains/train-columns-stats.component";
// import {TrainFeatureCorrelationStatsComponent} from "./trains/train-feature-correlation-stats.component";
// import {LoginComponent} from "./login/login.component"
// import {LoginGuard} from "./login/login-guard.component"
// import {AccountCreateComponent} from "./security/account/account-create.component";
// import {RoleCreateComponent} from "./security/role/role-create.component";
// import {RoleListComponent} from "./security/role/role-list.component";
// import {AccountListComponent} from "./security/account/account-list.component";
// import {SessionExpiredComponent} from "./login/sessionExpired.component";
// import {HdfsDownloadComponent} from "./hdfs/hdfs-download.component";
//
// const routes: Routes = [
//     {
//         path: "models",
//         component: ModelsComponent,
//         canActivate: [LoginGuard]
//     },
//     {
//         path: "models/:name",
//         component: ModelsComponent,
//         canActivate: [LoginGuard]
//     },
//     {
//         path: "tasks",
//         component: JobTrackerComponent,
//         canActivate: [LoginGuard]
//     },
//     {
//         path: "",
//         redirectTo: "/models",
//         pathMatch: "full",
//         canActivate: [LoginGuard]
//     },
//     {
//         path: "detail/:name",
//         component: ModelDetailComponent,
//         canActivate: [LoginGuard]
//     },
//     {
//         path: "model-create",
//         component: ModelCreateComponent,
//         canActivate: [LoginGuard]
//     },
//     {
//         path: "jobdetail/:name",
//         component: JobDetailComponent,
//         canActivate: [LoginGuard]
//     },
//     {
//         path: "evaluation/:algorithmConfigurationId",
//         component: AlgorithmEvaluationDetailsComponent,
//         canActivate: [LoginGuard]
//     },
//     {
//         path: "columnsstats/:trainId",
//         component: TrainColumnsStatsComponent,
//         canActivate: [LoginGuard]
//     },
//     {
//         path: "featurescorrelationstats/:trainId",
//         component: TrainFeatureCorrelationStatsComponent,
//         canActivate: [LoginGuard]
//     },
//     {
//         path: "login",
//         component: LoginComponent
//     },
//     {
//         path: "sessionExpired",
//         component: SessionExpiredComponent
//     },
//     {
//         path: "account-create",
//         component: AccountCreateComponent,
//         canActivate: [LoginGuard]
//     },
//     {
//         path: "account-modify/:name",
//         component: AccountCreateComponent,
//         canActivate: [LoginGuard]
//     },
//     {
//         path: "role-create",
//         component: RoleCreateComponent,
//         canActivate: [LoginGuard]
//     },
//     {
//         path: "role-modify/:role",
//         component: RoleCreateComponent,
//         canActivate: [LoginGuard]
//     },
//     {
//         path: "role-list",
//         component: RoleListComponent,
//         canActivate: [LoginGuard]
//     },
//     {
//         path: "account-list",
//         component: AccountListComponent,
//         canActivate: [LoginGuard]
//     },
//     {
//         path: "download",
//         component: HdfsDownloadComponent,
//         canActivate: [LoginGuard]
//     },
//     {
//         path: "**",
//         component: ModelsComponent,
//         canActivate: [LoginGuard]
//     },
// ];
//
// @NgModule({
//     imports: [RouterModule.forRoot(routes, {useHash: true})],
//     exports: [RouterModule]
// })
// export class AppRoutingModule { }