import tensorflow as tf
path = f"/home/yhk/Camera_pose/model/graph_opt.pb"


input_graph_def = tf.compat.v1.GraphDef()
with tf.io.gfile.GFile(path, 'rb') as f:
    input_graph_def.ParseFromString(f.read())

# SavedModel로 변환

tf.graph_util.import_graph_def(input_graph_def)
tf.saved_model.save(input_graph_def, "/home/yhk/Camera_pose/model/a.pb")