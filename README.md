# 课灵 - 游戏化学习助手

<p align="center">
  
</p>

<p align="center">
  <strong>让学习变成一场有趣的冒险</strong>
</p>

## 项目简介

**课灵**是一款专为大学生设计的Android端游戏化学习助手应用。通过将学习过程游戏化，结合AI技术和个性化推荐，帮助学生更高效、更有趣地完成学业任务。

### 核心特性

- 🎮 **游戏化任务系统** - 将学习任务转化为可完成的游戏关卡
- 🏆 **成就徽章体系** - 丰富的成就系统激励持续学习
- 🤖 **AI智能助手** - 多模态交互，实时解答学习问题
- 📊 **学情诊断分析** - 精准定位知识薄弱点
- 🌐 **知识图谱** - 可视化展示知识点关联
- ♿ **无障碍支持** - 完善的视觉、听觉、运动辅助功能
- 🎨 **科幻UI设计** - 霓虹风格，沉浸式体验

## 技术栈

### 前端
- **语言**: Kotlin 1.9.20
- **UI框架**: Jetpack Compose
- **架构**: MVVM + Clean Architecture
- **依赖注入**: Hilt
- **导航**: Navigation Compose

### 数据层
- **本地数据库**: Room
- **网络请求**: Retrofit + OkHttp
- **数据存储**: DataStore Preferences
- **图片加载**: Coil

### 功能组件
- **动画**: Lottie
- **图表**: Vico Charts
- **OCR**: ML Kit
- **相机**: CameraX
- **生物识别**: BiometricPrompt

## 项目结构

```
app/
├── src/main/
│   ├── java/com/keling/app/
│   │   ├── data/
│   │   │   ├── local/          # 本地数据库
│   │   │   │   ├── dao/        # 数据访问对象
│   │   │   │   └── KelingDatabase.kt
│   │   │   ├── remote/         # 网络API
│   │   │   ├── model/          # 数据模型
│   │   │   └── repository/     # 数据仓库
│   │   ├── di/                 # 依赖注入模块
│   │   ├── ui/
│   │   │   ├── components/     # 通用UI组件
│   │   │   ├── navigation/     # 导航配置
│   │   │   ├── screens/        # 页面
│   │   │   │   ├── home/
│   │   │   │   ├── tasks/
│   │   │   │   ├── courses/
│   │   │   │   ├── achievements/
│   │   │   │   ├── profile/
│   │   │   │   ├── ai/
│   │   │   │   ├── settings/
│   │   │   │   ├── login/
│   │   │   │   └── splash/
│   │   │   └── theme/          # 主题配置
│   │   ├── util/               # 工具类
│   │   ├── KelingApplication.kt
│   │   └── MainActivity.kt
│   └── res/
│       ├── drawable/
│       ├── mipmap-*/
│       ├── values/
│       └── xml/
├── build.gradle.kts
└── proguard-rules.pro
```

## 快速开始

### 环境要求
- Android Studio Hedgehog (2023.1.1) 或更高版本
- JDK 17
- Android SDK 34
- Gradle 8.2

### 构建步骤

1. **克隆项目**
   ```bash
   git clone https://github.com/your-org/keling.git
   cd keling
   ```

2. **打开项目**
   使用 Android Studio 打开项目根目录

3. **同步依赖**
   等待 Gradle 同步完成

4. **运行应用**
   选择模拟器或真机，点击运行

### 配置说明

在 `local.properties` 中添加以下配置（可选）：
```properties
# API配置
KELING_API_URL=https://api.keling.edu.cn/
EDUCATION_API_URL=https://api.campus.edu.cn/
```

## 功能模块

### 1. 用户系统
- 多角色登录（学生/教师/家长）
- OAuth2.0校园统一身份认证
- 生物识别登录
- 个人数据看板

### 2. 课程管理
- 教务系统数据同步
- 智能课表生成
- 课程进度追踪
- 教材数字化（OCR）

### 3. 任务系统
- 三维任务地图
- 动态难度算法
- 组队协作模式
- 任务进度同步

### 4. 游戏化引擎
- 粒子特效系统
- 成就徽章体系
- 经验值系统
- 排行榜

### 5. AI助手
- 多模态交互（语音/文字/手势）
- 学情诊断
- 知识图谱
- 智能推荐

### 6. 无障碍系统
- 字体大小调节
- 高对比度模式
- TTS语音朗读
- 手势控制

## 设计规范

### 配色方案（科幻霓虹风格）

| 颜色名称 | 色值 | 用途 |
|---------|------|------|
| NeonBlue | #00D4FF | 主色调 |
| NeonPurple | #BF00FF | 强调色 |
| NeonPink | #FF0080 | 辅助色 |
| NeonGreen | #00FF88 | 成功状态 |
| NeonOrange | #FF6600 | 警告状态 |
| NeonRed | #FF0040 | 错误状态 |
| NeonGold | #FFD700 | 奖励/成就 |

### 深色背景

| 颜色名称 | 色值 | 用途 |
|---------|------|------|
| DarkBackground | #0A0E17 | 页面背景 |
| DarkSurface | #121824 | 卡片背景 |
| DarkCard | #1E2838 | 组件背景 |
| DarkBorder | #2A3648 | 边框 |

## 团队分工

| 角色 | 职责 |
|-----|------|
| 前端开发 (2人) | UI实现、交互逻辑、动画效果 |
| 后端开发 (1人) | API接口、数据库设计、服务端部署 |
| 算法/测试 (1人) | AI模型优化、性能调优、自动化测试 |

## 性能指标

| 测试项 | 工具 | 合格标准 |
|-------|------|---------|
| 冷启动时间 | Android Profiler | ≤1.2秒 |
| 列表滚动帧率 | Systrace | ≥60fps |
| 内存泄漏 | LeakCanary | 无严重泄漏 |
| APK大小 | - | ≤50MB |

## 版本历史

### v1.0.0 (开发中)
- 初始版本
- 核心功能实现
- 科幻UI设计

## 贡献指南

1. Fork 本项目
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 创建 Pull Request

## 许可证

本项目仅供学习和教育目的使用。

## 联系方式

- 项目主页: [https://github.com/your-org/keling](https://github.com/your-org/keling)
- 问题反馈: [Issues](https://github.com/your-org/keling/issues)

---

<p align="center">
  Made with ❤️ by 课灵团队
</p>
